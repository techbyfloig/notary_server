package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class EstimatedArrival(
    val BusinessDaysInTransit: String,
    val CustomerCenterCutoff: String,
    val DayOfWeek: String,
    val DelayCount: String,
    val HolidayCount: String,
    val Pickup: Pickup,
    val RestDays: String,
    val TotalTransitDays: String
)