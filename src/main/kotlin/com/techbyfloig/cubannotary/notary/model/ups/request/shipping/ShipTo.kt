package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class ShipTo(
    val Address: Address,
    val AttentionName: String,
    val Name: String,
    val Phone: Phone,
    val Residential: String
)