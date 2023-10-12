package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class TimeInTransit(
    val AutoDutyCode: String,
    val Disclaimer: String,
    val DocumentsOnlyIndicator: String,
    val PackageBillType: String,
    val PickupDate: String,
    val ServiceSummary: ServiceSummary
)