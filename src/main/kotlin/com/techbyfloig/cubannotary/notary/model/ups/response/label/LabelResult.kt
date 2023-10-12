package com.techbyfloig.cubannotary.android.network.models.response.label

data class LabelResult(
    val LabelImage: LabelImage,
    val Receipt: Receipt,
    val TrackingNumber: String
)