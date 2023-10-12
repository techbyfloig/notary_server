package com.techbyfloig.cubannotary.android.network.models.response.shipping

data class ShipmentCharges(
    val ItemizedCharges: ItemizedCharges,
    val ServiceOptionsCharges: ServiceOptionsCharges,
    val TotalCharges: TotalCharges,
    val TransportationCharges: TransportationCharges
)