package com.techbyfloig.cubannotary.notary.repo

import com.techbyfloig.cubannotary.notary.env.Environment
import com.techbyfloig.cubannotary.notary.model.ups.response.oauth.token.TokenResp
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.util.Base64Utils
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

class UpsOauthNetwork(
    private val restTemplate: RestTemplate
) {

    private val clientKey = Environment.UpsApisConst.CLIENT_KEY
    private val secretKey = Environment.UpsApisConst.SECRET_KEY

    fun getTokenEntity(): Result<TokenResp> {
        val url = Environment.UpsApisConst.Oauth.TOKEN_PATH
        val grantType = "client_credentials"

        // Create URI with path variable
        val basicAuthHeader = "Basic ${Base64Utils.encodeToString("$clientKey:$secretKey".toByteArray())}"
        val headers = HttpHeaders().apply {
            set("Authorization", basicAuthHeader)
            set("x-merchant-id", clientKey)
            contentType = MediaType.APPLICATION_FORM_URLENCODED // Add the Authorization header with the Bearer token
        }

        // Create a MultiValueMap with form data for form-urlencoded
        val body: MultiValueMap<String, String> = LinkedMultiValueMap<String, String>().apply {
            add(Environment.UpsApisConst.GRANT_TYPE, grantType)
        }

        val httpEntity = HttpEntity(body, headers)

        val uri = UriComponentsBuilder
            .fromHttpUrl(url)
            .build()
            .toUri()

        // Make a POST request with path variable and request body
        val response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, TokenResp::class.java).body
        return if (response != null) {
            Result.success(response)
        } else {
            Result.failure(Exception("Error getting token"))
        }
    }


}