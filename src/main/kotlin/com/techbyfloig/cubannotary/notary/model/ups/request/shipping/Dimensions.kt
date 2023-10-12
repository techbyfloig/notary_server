package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class Dimensions(
    val Height: String,
    val Length: String,
    val UnitOfMeasurement: UnitOfMeasurement,
    val Width: String
)