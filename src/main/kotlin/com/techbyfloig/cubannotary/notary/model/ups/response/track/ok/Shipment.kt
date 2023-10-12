package com.techbyfloig.cubannotary.android.network.models.response.track.ok

data class Shipment(
    val inquiryNumber: String,
    val `package`: List<Package>
)