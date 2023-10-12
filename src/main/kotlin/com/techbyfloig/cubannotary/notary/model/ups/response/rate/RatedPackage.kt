package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class RatedPackage(
    val BillingWeight: BillingWeight,
    val ServiceOptionsCharges: Charges,
    val TotalCharges: Charges,
    val TransportationCharges: Charges,
    val Weight: String,
    val SimpleRate: SimpleRate
)




