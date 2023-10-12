package com.techbyfloig.cubannotary.notary.utils

import java.io.ByteArrayOutputStream
import java.security.SecureRandom
import java.text.DecimalFormat
import java.util.*

fun generateRandomKey(length: Int): ByteArray {
    val secureRandom = SecureRandom()
    val key = ByteArray(length)
    secureRandom.nextBytes(key)
    return key
}

fun longToDateParsed(time: Long): List<String> {
    val date = Date(time)
    val day = date.day
    val month = date.month
    val year = date.year

    return listOf(day.toString(), month.toString(), year.toString())
}

fun roundToTwoDecimalPlaces(value: Double): Double {
    val decimalFormat = DecimalFormat("#.##")
    return decimalFormat.format(value).toDouble()
}
