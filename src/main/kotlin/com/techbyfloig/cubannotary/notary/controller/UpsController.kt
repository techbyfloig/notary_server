package com.techbyfloig.cubannotary.notary.controller

import com.techbyfloig.cubannotary.notary.model.ups.request.rate.RateReq
import com.techbyfloig.cubannotary.notary.service.UpsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/ups")
class UpsController {

    @Autowired
    private val upsService: UpsService? = null

    @PostMapping("rate")
    fun getRatings(
        @RequestHeader("DeviceId") deviceId: Long,
        @RequestHeader("Authorization", required = false) token: String?,
        @RequestBody reqBody: RateReq
    ): ResponseEntity<out Any> {

        val response = upsService?.getRates(reqBody)

        return if (response?.isSuccess == true) {
            val data = response.getOrNull() ?: ResponseEntity( "Not rate found", HttpStatus.NOT_FOUND)
            ResponseEntity(data, HttpStatus.OK)
        } else ResponseEntity( "Error getting the rate", HttpStatus.BAD_REQUEST)

    }

}
