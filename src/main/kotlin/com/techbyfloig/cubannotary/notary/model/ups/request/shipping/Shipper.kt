package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class Shipper(
    val Address: Address,
    val AttentionName: String,
    val FaxNumber: String,
    val Name: String,
    val Phone: Phone,
    val ShipperNumber: String,
    val TaxIdentificationNumber: String
)