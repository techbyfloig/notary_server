package com.techbyfloig.cubannotary.notary.conf

import com.techbyfloig.cubannotary.notary.model.UserEntity
import com.techbyfloig.cubannotary.notary.repo.JwtKeyRepo
import com.techbyfloig.cubannotary.notary.repo.UserRepo
import com.techbyfloig.cubannotary.notary.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthFilter : OncePerRequestFilter() {

    @Autowired
    private val jwtToken: JwtTokenUtil? = null

    @Autowired
    private val userDetailService: UserService? = null

    @Autowired
    private val jwtKeyRepo: JwtKeyRepo? = null

    @Autowired
    private val userRepo: UserRepo? = null

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val token = getJWTAccessTokenFromRequest(request)
        val refresh = getJWTRefreshTokenFromRequest(request)
        val deviceId = getDeviceIdFromRequest(request) ?: ""

        if (!refresh.isNullOrEmpty()) {

            val listKeys = jwtKeyRepo?.getKeysByAudience(deviceId) ?: listOf()

            val listRefreshKey = listKeys.map {
                decodeSecretKey(it.refreshSecretKey)
            }

            val refreshTokenKey = jwtToken?.validateToken(refresh, listRefreshKey)

            if (refreshTokenKey != null && jwtToken?.isTokenExpired(refresh, refreshTokenKey) == false) {
                val userDetails = authenticateUser(refresh, refreshTokenKey)
                request.setAttribute("userDetails", userDetails)
                val authenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    null
                )
                authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            } else response.sendError(401, "Unauthorized")

        } else {
            if (!token.isNullOrEmpty()) {

                val listKeys = jwtKeyRepo?.getKeysByAudience(deviceId) ?: listOf()

                val listAccessKey = listKeys.map {
                    decodeSecretKey(it.accessSecretKey)
                }

                val accessTokenKey = jwtToken?.validateToken(token, listAccessKey)

                if (accessTokenKey != null && jwtToken?.isTokenExpired(token, accessTokenKey) == false) {
                    val username = token.let { jwtToken.extractUsernameFromToken(it, accessTokenKey) }
                    val userDetails = username.let { userDetailService?.loadUserByUsername(it) }
                    val authenticationToken = UsernamePasswordAuthenticationToken(
                       userDetails,
                        null,
                        null
                    )
                    authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authenticationToken
                } else response.sendError(401, "Unauthorized")
            }
        }

        filterChain.doFilter(request, response)

    }

    private fun getJWTAccessTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7, bearerToken.length)
        } else null
    }

    private fun getJWTRefreshTokenFromRequest(request: HttpServletRequest): String? {
        val refreshToken = request.getParameter("refresh_token")
        return if (StringUtils.hasText(refreshToken)) {
            refreshToken
        } else null
    }

    private fun getDeviceIdFromRequest(request: HttpServletRequest): String? {
        val id = request.getHeader("DeviceId")
        return if (id.isNullOrEmpty()) null else id
    }

    private fun decodeSecretKey(encodedKey: String, algorithm: String = "HmacSHA256"): SecretKey {
        val decodedKey = Base64.getDecoder().decode(encodedKey)
        return SecretKeySpec(decodedKey, algorithm)
    }

    private fun authenticateUser(token: String?, key: SecretKey): UserEntity? {
        return if (!token.isNullOrEmpty()) {
            val username = jwtToken?.extractUsernameFromToken(token, key)
            val user = username?.let {
                userRepo?.findByUsername(it)
            }
            user?.get()
        } else null

    }

}