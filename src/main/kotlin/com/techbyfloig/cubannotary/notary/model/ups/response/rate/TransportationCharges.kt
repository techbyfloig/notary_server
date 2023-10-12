package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class TransportationCharges(
    val DiscountAmount: DiscountAmount,
    val DiscountPercentage: String,
    val GrossCharge: GrossCharge,
    val NetCharge: NetCharge
)