package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class Shipment(
    val Description: String,
    val Package: Package,
    val PaymentInformation: PaymentInformation,
    val Service: Service,
    val ShipFrom: ShipFrom,
    val ShipTo: ShipTo,
    val Shipper: Shipper
)