package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class Shipment(
    @SerializedName("NumOfPieces")
    val numOfPieces: String,
    @SerializedName("Package")
    val pack: Package,
    @SerializedName("PaymentDetails")
    val paymentDetails: PaymentDetails,
    @SerializedName("Service")
    val service: Service,
    @SerializedName("ShipFrom")
    val shipFrom: ShipFrom,
    @SerializedName("ShipTo")
    val shipTo: ShipTo,
    @SerializedName("Shipper")
    val shipper: Shipper
)