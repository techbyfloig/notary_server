package com.techbyfloig.cubannotary.android.network.models.response.label

data class TrackingCandidate(
    val DestinationCountryCode: String,
    val DestinationPostalCode: String,
    val PickupDateRange: PickupDateRange,
    val TrackingNumber: String
)