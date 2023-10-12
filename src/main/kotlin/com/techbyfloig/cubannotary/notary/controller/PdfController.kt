package com.techbyfloig.cubannotary.notary.controller

import com.techbyfloig.cubannotary.notary.service.ApplicationService
import com.techbyfloig.cubannotary.notary.service.PdfServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource
import org.springframework.core.io.ByteArrayResource
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream
import java.util.zip.ZipOutputStream


@RestController
@RequestMapping("/api/pdfs")
class PdfController {

    @Autowired
    val pdfServices: PdfServices? = null

    @GetMapping
    fun getPdfs(
        @RequestParam("type") type: Int
    ): ResponseEntity<out Any> {

        // Generate PDFs and add them to the zip
        val passportZipPdfs = pdfServices?.getPassportZipPdfs(type)

        return if (passportZipPdfs?.isSuccess == true) {
            // Set headers for the response
            val headers = HttpHeaders()
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=passports.zip")

            ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(passportZipPdfs.getOrNull() ?: ResponseEntity("not documents to print", HttpStatus.NOT_FOUND))

        } else {
            ResponseEntity("Something wrong with data", HttpStatus.NOT_FOUND)
        }

    }
}