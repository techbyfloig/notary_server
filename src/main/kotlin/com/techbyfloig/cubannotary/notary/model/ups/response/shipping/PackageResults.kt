package com.techbyfloig.cubannotary.android.network.models.response.shipping

data class PackageResults(
    val BaseServiceCharge: BaseServiceCharge,
    val ItemizedCharges: List<ItemizedCharge>,
    val ServiceOptionsCharges: ServiceOptionsCharges,
    val ShippingLabel: ShippingLabel,
    val TrackingNumber: String
)