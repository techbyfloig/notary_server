package com.techbyfloig.cubannotary.notary.service

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.gson.Gson
import com.techbyfloig.cubannotary.notary.model.UserEntity
import org.springframework.stereotype.Service

@Service
class GoogleUserService {

    private val transport = NetHttpTransport()
    private val jsonFactory = JacksonFactory()

    fun validateGoogleToken(tokenId: String): Result<UserEntity> {
        val verifier = GoogleIdTokenVerifier.Builder(
            transport,
            jsonFactory
        ) // Specify the CLIENT_ID of the app that accesses the backend:
            .setAudience(listOf(CLIENT_ID))// Or, if multiple clients access the backend:
//            .setAudience(listOf(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
            .build()

        try {
            val googleIdToken = verifier.verify(tokenId)
            val payload = googleIdToken.payload

            val user = with(payload){
                UserEntity(
                    id = subject.toLong(),
                    username = email,
                    password = "",
                    fullName = payload["name"] as String,
                    provider = issuer,
                    enabled = true,
                    audience = Gson().toJson(audienceAsList),
                    pictureUrl = payload["picture"] as String
                )
            }
            return Result.success(user)

        }catch (ex: Exception){
            return Result.failure(ex)
        }


        // Print user identifier
//        val userId: String = payload.subject
//
//        // Get profile information from payload
//        val email: String = payload.email
//        val emailVerified: Boolean = java.lang.Boolean.valueOf(payload.emailVerified)
//        val name = payload["name"]
//        val pictureUrl = payload["picture"]
//        val locale = payload["locale"]
//        val familyName = payload["family_name"]
//        val givenName = payload["given_name"]
    }

    companion object{
        private const val CLIENT_ID = "457896630352-mi3j0ttbuc5oop0oif37qesn3u0ih6mk.apps.googleusercontent.com"
        private const val CLIENT_ID_1 = "457896630352-9fisbsktpbe8fq30a3aocpcda63m3vtg.apps.googleusercontent.com"
        private const val CLIENT_ID_2 = "457896630352-6d2al3noqt1p2l46bi497d6b7llp47mf.apps.googleusercontent.com"
        private const val CLIENT_ID_3 = "457896630352-89349dejlafmuev56i66h6bhb9l4r0n3.apps.googleusercontent.com"
    }

}