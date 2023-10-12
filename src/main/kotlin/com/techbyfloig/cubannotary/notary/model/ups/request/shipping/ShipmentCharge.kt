package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class ShipmentCharge(
    val BillShipper: BillShipper,
    val Type: String
)