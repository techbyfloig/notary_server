package com.techbyfloig.cubannotary.notary.service

import com.techbyfloig.cubannotary.notary.env.Environment
import com.techbyfloig.cubannotary.notary.model.ups.request.rate.RateReq
import com.techbyfloig.cubannotary.notary.model.ups.response.rate.RateResp
import com.techbyfloig.cubannotary.notary.repo.UpsOauthRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Service
class UpsService(
    private val restTemplate: RestTemplate,
) {

    @Autowired
    private val upsRepo: UpsOauthRepo? = null

    fun getRates(reqBody: RateReq): Result<RateResp> {
        val version = "v1"
        val requestOption = "shop"
        val url = Environment.UpsApisConst.Rating.PATH

        // Create URI with path variable
        val uri = UriComponentsBuilder.fromHttpUrl(url)
            .buildAndExpand(
                mapOf(
                    Environment.UpsApisConst.VERSION to version,
                    Environment.UpsApisConst.Rating.PathParameters.REQUEST_OPT to requestOption
                )
            )
            .toUri()

        // Create HttpEntity with request body
        val token = upsRepo?.getToken() ?: Result.failure(Exception("Error getting token"))
        if (token.isFailure) return Result.failure(Exception("Error getting token"))

        val headers = HttpHeaders().apply {
            set("Authorization", "Bearer ${token.getOrNull() ?: ""}") // Add the Authorization header with the Bearer token
            contentType = MediaType.APPLICATION_JSON
        }

        val requestEntity = HttpEntity(reqBody, headers)

        // Make a POST request with path variable and request body
        val response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, RateResp::class.java).body
        return if (response != null) {
            Result.success(response)
        } else {
            Result.failure(Exception("Error getting rates"))
        }
    }

}