package com.techbyfloig.cubannotary.notary.controller

import com.techbyfloig.cubannotary.notary.model.AuthReq
import com.techbyfloig.cubannotary.notary.model.AuthResponseDTO
import com.techbyfloig.cubannotary.notary.model.UserEntity
import com.techbyfloig.cubannotary.notary.repo.RoleRepo
import com.techbyfloig.cubannotary.notary.repo.UserRepo
import com.techbyfloig.cubannotary.notary.conf.JwtTokenUtil
import com.techbyfloig.cubannotary.notary.service.UserService
import com.techbyfloig.cubannotary.notary.service.GoogleUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@RestController
@RequestMapping("/api/auth")
class SecurityController {


    private val userAuthService: UserService
    private val userRepo: UserRepo
    private val authenticationManager: AuthenticationManager
    private val roleRepo: RoleRepo
    private val jwtTokenUtil: JwtTokenUtil
    private val googleUserService: GoogleUserService
    private val passwordEncoder: PasswordEncoder


    @Autowired
    constructor(
        userAuthService: UserService,
        userRepo: UserRepo,
        authenticationManager: AuthenticationManager,
        roleRepo: RoleRepo,
        jwtTokenUtil: JwtTokenUtil,
        googleUserService: GoogleUserService,
        passwordEncoder: PasswordEncoder
    ) {
        this.userAuthService = userAuthService
        this.userRepo = userRepo
        this.authenticationManager = authenticationManager
        this.roleRepo = roleRepo
        this.jwtTokenUtil = jwtTokenUtil
        this.googleUserService = googleUserService
        this.passwordEncoder = passwordEncoder
    }


    @PostMapping("signUp")
    fun registerUser(
        @RequestHeader deviceId: String,
        @RequestBody request: AuthReq
    ): ResponseEntity<Any> {

        val userCreatedResult = when (request.googleToken.isNullOrEmpty()) {
            false -> {
                val googleResult = googleUserService.validateGoogleToken(request.googleToken)

                if (googleResult.isFailure) {
                    return ResponseEntity(
                        "google validation failed ${googleResult.exceptionOrNull()}",
                        HttpStatus.BAD_REQUEST
                    )
                }

                val googleUser = googleResult.getOrNull() ?: return ResponseEntity(
                    "null result from validation ",
                    HttpStatus.BAD_REQUEST
                )
                val userExist = userRepo.existsByUsername(googleUser.username)

                if (userExist) return ResponseEntity("User already exist", HttpStatus.BAD_REQUEST)
                // create a new user
                userAuthService.createNewUser(googleUser)

            }

            true -> {
                // check if user exist
                val userExist = userRepo.existsByUsername(request.username)

                if (userExist) return ResponseEntity("User already exist", HttpStatus.BAD_REQUEST)
                // create a new user
                val userNew = UserEntity(
                    username = request.username,
                    password = passwordEncoder.encode(request.password),
                    enabled = true,
                    provider = request.provider,
                    audience = deviceId,
                    fullName = request.name
                    // roles = arrayListOf(Roles())
                )
                userAuthService.createNewUser(userNew)
            }
        }

        if (userCreatedResult.isFailure)
            return ResponseEntity(
                "not user created, error: ${userCreatedResult.exceptionOrNull()}",
                HttpStatus.CONFLICT
            )

        return ResponseEntity("User Created", HttpStatus.OK)

    }


    @PostMapping("signIn")
    fun signInUser(
        @RequestHeader deviceId: String,
        @RequestBody request: AuthReq
    ): ResponseEntity<String> {

        val tokens = when (request.googleToken.isNullOrEmpty()) {
            false -> {
                val googleResult = googleUserService.validateGoogleToken(request.googleToken)
                if (googleResult.isFailure) {
                    return ResponseEntity("google token validation failed", HttpStatus.UNAUTHORIZED)
                }
                val googleUser = googleResult.getOrNull()
                    ?: return ResponseEntity("Internal issue", HttpStatus.INTERNAL_SERVER_ERROR)

                val authentication = UsernamePasswordAuthenticationToken(
                    googleUser.username,
                    googleUser.password,
                    null
                )
                SecurityContextHolder.getContext().authentication = authentication
                jwtTokenUtil.generateTokens(googleUser)
            }

            true -> {
                val authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(
                        request.username,
                        request.password,
                        null
                    )
                ) ?: return ResponseEntity("Unauthorized user", HttpStatus.UNAUTHORIZED)

                SecurityContextHolder.getContext().authentication = authentication

                val userEntity = userRepo.findByUsername(request.username).orElseThrow {
                    UsernameNotFoundException("User name with ${request.username} not found")
                }
                jwtTokenUtil.generateTokens(userEntity)
            }
        }

        return ResponseEntity(AuthResponseDTO(tokens).generateTokenResponse(), HttpStatus.OK)
    }

    @PostMapping("refresh", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun tokenExpiration(
        @RequestHeader("DeviceId") deviceId: String,
        @RequestParam("refresh_token") refreshToken: String
    ): ResponseEntity<String> {

        val requestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?
        val user = requestAttributes?.request?.getAttribute("userDetails") as UserEntity?
            ?: return ResponseEntity("Unauthorized user", HttpStatus.UNAUTHORIZED)

        val accessPair = jwtTokenUtil.generateAccessToken(user)

        jwtTokenUtil.saveKeys(user, refreshKey = "", accessPair.first)

        return ResponseEntity("access_token: ${accessPair.second}" , HttpStatus.OK)
    }

}