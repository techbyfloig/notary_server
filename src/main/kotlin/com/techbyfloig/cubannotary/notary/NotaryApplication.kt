package com.techbyfloig.cubannotary.notary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class NotaryApplication

fun main(args: Array<String>) {
	runApplication<NotaryApplication>(*args)
}

//username : user
//Using generated security password: f2f2a14e-d54a-4af3-82a5-3b54509f8a29
