package com.techbyfloig.cubannotary.android.network.models.response.shipping

data class ShipmentResults(
    val BillingWeight: BillingWeight,
    val PackageResults: PackageResults,
    val ShipmentCharges: ShipmentCharges,
    val ShipmentIdentificationNumber: String
)