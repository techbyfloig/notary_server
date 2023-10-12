package com.techbyfloig.cubannotary.notary.conf

import com.techbyfloig.cubannotary.notary.model.JwtSecretKey
import com.techbyfloig.cubannotary.notary.model.UserEntity
import com.techbyfloig.cubannotary.notary.repo.JwtKeyRepo
import com.techbyfloig.cubannotary.notary.service.JwtKeyService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JwtTokenUtil {

    private val jwtKeyRepo: JwtKeyRepo
    private val jwtKeyService: JwtKeyService

    @Autowired
    constructor(jwtKeyRepo: JwtKeyRepo, jwtKeyService: JwtKeyService) {
        this.jwtKeyRepo = jwtKeyRepo
        this.jwtKeyService = jwtKeyService
    }

    fun generateTokens(userEntity: UserEntity): List<Pair<String, String>> {
        val tokens = mutableListOf<Pair<String, String>>()

        val refreshMap = generateRefreshToken(userEntity)
        val accessMap = generateAccessToken(userEntity)

        saveKeys(userEntity, refreshMap.first, accessMap.first)

        tokens.add(Pair("refresh_token", refreshMap.second))
        tokens.add(Pair("access_token", accessMap.second))

        return tokens
    }

    fun generateAccessToken(user: UserEntity): Pair<String, String> {
        val accessTokenKey = secretKey
        val accessToken = Jwts.builder()
            .setSubject(user.username)
            .claim("fullName", user.fullName)
            .claim("provider", user.provider)
            .claim("picture", user.pictureUrl)
            .claim("userId", user.id)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + accessTokenValidity))
            .signWith(accessTokenKey, SignatureAlgorithm.HS512)
            .compact()

        val encodedKey = Base64.getEncoder().encodeToString(accessTokenKey.encoded)

        return Pair(encodedKey, accessToken)
    }

    fun generateRefreshToken(userEntity: UserEntity): Pair<String, String> {
        val refreshTokenKey = secretKey
        val refreshToken = Jwts.builder()
            .setSubject(userEntity.username)
            .claim("audience", userEntity.audience)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + refreshTokenValidity))
            .signWith(refreshTokenKey, SignatureAlgorithm.HS512)
            .compact()

        val encodedKey = Base64.getEncoder().encodeToString(refreshTokenKey.encoded)

        return Pair(encodedKey, refreshToken)
    }

    fun validateToken(token: String?, listOfKeys: List<SecretKey>): SecretKey? {
        for (keys in listOfKeys) {
            // Parse the token and verify its signature using the secret key
            if (Jwts.parser().setSigningKey(keys).parseClaimsJws(token) != null)
                return keys
        }
        // Token validation failed (e.g., signature verification failed or token expired)
        return null
    }

    fun isTokenExpired(token: String?, key: SecretKey): Boolean {
        // Parse the token and verify its signature using the secret key
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token).body.expiration.before(Date())
    }

    fun extractUsernameFromToken(token: String, key: SecretKey): String {
        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body;
        return claims.subject;
    }

    fun getClaimsFromToken(token: String, key: SecretKey): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token).body
    }

    fun saveKeys(user: UserEntity, refreshKey: String, accessKey: String) {
        when {
            refreshKey.isNotEmpty() && accessKey.isNotEmpty() -> {
                val newJwtKey = JwtSecretKey(
                    id = user.id,
                    audience = user.audience,
                    accessSecretKey = accessKey,
                    refreshSecretKey = refreshKey
                )
                jwtKeyRepo.save(newJwtKey)
            }
            refreshKey.isEmpty() && accessKey.isNotEmpty() -> {
                jwtKeyService.addAccessKey(user.id ,accessKey)
            }
            refreshKey.isNotEmpty() && accessKey.isEmpty() -> {
                jwtKeyService.addRefreshKey(user.id ,refreshKey)
            }

        }

    }


    companion object {
        private val secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
        private const val refreshTokenValidity = 60000000
        private const val accessTokenValidity = 60000000
    }
}