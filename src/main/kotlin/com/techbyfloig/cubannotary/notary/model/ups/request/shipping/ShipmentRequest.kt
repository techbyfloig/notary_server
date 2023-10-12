package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class ShipmentRequest(
    val LabelSpecification: LabelSpecification,
    val Request: Request,
    val Shipment: Shipment
)