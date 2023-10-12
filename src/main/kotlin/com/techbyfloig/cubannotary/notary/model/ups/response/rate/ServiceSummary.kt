package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class ServiceSummary(
    val Disclaimer: String,
    val EstimatedArrival: EstimatedArrival,
    val GuaranteedIndicator: String,
    val SaturdayDelivery: String,
    val SaturdayDeliveryDisclaimer: String,
    val Service: Service,
    val SundayDelivery: String,
    val SundayDeliveryDisclaimer: String
)