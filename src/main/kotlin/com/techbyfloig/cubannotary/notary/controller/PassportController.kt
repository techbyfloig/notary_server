package com.techbyfloig.cubannotary.notary.controller

import com.techbyfloig.cubannotary.notary.model.Response
import com.techbyfloig.cubannotary.notary.model.UserServiceDetails
import com.techbyfloig.cubannotary.notary.conf.JwtTokenUtil
import com.techbyfloig.cubannotary.notary.service.ApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/applications")
class PassportController {

    @Autowired
    val appService: ApplicationService? = null


    @Autowired
    val jwtToken: JwtTokenUtil? = null


    @GetMapping
    fun getApplications(
        @RequestHeader("DeviceId") deviceId: Long,
        @RequestHeader("Authorization") token: String
    ): ResponseEntity<out Any> {

        val authentication = SecurityContextHolder.getContext().authentication
        val user = authentication.principal as UserServiceDetails

        val apps = appService?.getApplications(user.users.id)
            ?: return ResponseEntity("No applications found", HttpStatus.NOT_FOUND)

        val response = if (apps.isSuccess) {
            val data = apps.getOrNull() ?: ""
            if (data.isEmpty()) ResponseEntity("No applications found", HttpStatus.NOT_FOUND)
            ResponseEntity(data, HttpStatus.OK)
        } else ResponseEntity("No applications found", HttpStatus.NOT_FOUND)

        return response
    }

    @GetMapping("/{appId}")
    fun getApplication(
        @RequestHeader("DeviceId") deviceId: String,
        @RequestHeader("Authorization") token: String,
        @RequestParam appType: Int,
        @PathVariable appId: Long
    ): ResponseEntity<out Any> {

        val app = appService?.getApplication(appId, appType)

        val response = if (app?.isSuccess == true) {
            val data = app.getOrNull() ?: ""
            if(data == "") ResponseEntity("No application found", HttpStatus.NOT_FOUND)
            ResponseEntity(Response(listOf(data)).toJson(), HttpStatus.OK)
        } else ResponseEntity("No application found", HttpStatus.NOT_FOUND)

        return response
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveApplication(
        @RequestHeader("DeviceId") deviceId: String,
        @RequestHeader("Authorization") token: String,
        @RequestParam appType: Int,
        @RequestBody application: String
    ): ResponseEntity<out Any> {

        val result = appService?.saveApplication(application, appType)

        return if (result?.isSuccess == true) {
            ResponseEntity("Application Saved", HttpStatus.OK)
        } else {
            ResponseEntity("Application Not Saved", HttpStatus.BAD_REQUEST)
        }

    }

    @DeleteMapping("/{appId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteApplication(
        @RequestHeader("DeviceId") deviceId: String,
        @RequestHeader("Authorization") token: String,
        @RequestParam appType: Int,
        @PathVariable appId: Long
    ): ResponseEntity<out Any> {

        val app = appService?.deleteApplication(appId, appType)

        return  if (app?.isSuccess == true) {
            ResponseEntity("Application deleted", HttpStatus.OK)
        } else ResponseEntity("Nothing to delete", HttpStatus.NOT_FOUND)

    }

}
