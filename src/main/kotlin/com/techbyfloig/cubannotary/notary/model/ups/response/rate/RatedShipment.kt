package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class RatedShipment(
    val BillingWeight: BillingWeight,//
    val RatedPackage: RatedPackage,
    val RatedShipmentAlert: Alert,//
    val Service: Service, //
    val ServiceOptionsCharges: Charges,//
    val TotalCharges: Charges,//
    val TransportationCharges: Charges //
)