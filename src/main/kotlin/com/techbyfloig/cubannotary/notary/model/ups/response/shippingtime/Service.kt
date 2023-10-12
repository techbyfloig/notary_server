package com.techbyfloig.cubannotary.android.network.models.response.shippingtime

data class Service(
    val businessTransitDays: Int,
    val commitTime: String,
    val cstccutoffTime: String,
    val delayCount: Int,
    val deliveryDate: String,
    val deliveryDayOfWeek: String,
    val deliveryTime: String,
    val guaranteeIndicator: String,
    val holidayCount: Int,
    val nextDayPickupIndicator: String,
    val pickupDate: String,
    val pickupTime: String,
    val poddate: String,
    val poddays: Int,
    val restDaysCount: Int,
    val saturdayDeliveryDate: String,
    val saturdayDeliveryTime: String,
    val saturdayPickupIndicator: String,
    val serviceLevel: String,
    val serviceLevelDescription: String,
    val serviceRemarksText: String,
    val shipDate: String,
    val totalTransitDays: Int
)