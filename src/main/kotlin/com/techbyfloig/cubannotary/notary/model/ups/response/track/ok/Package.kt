package com.techbyfloig.cubannotary.android.network.models.response.track.ok

data class Package(
    val activity: List<Activity>,
    val deliveryDate: List<DeliveryDate>,
    val deliveryTime: DeliveryTime,
    val packageCount: Int,
    val trackingNumber: String
)