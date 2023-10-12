package com.techbyfloig.cubannotary.android.network.models.request.label

data class LabelRecoveryRequest(
    val LabelDelivery: LabelDelivery,
    val LabelSpecification: LabelSpecification,
    val Request: Request,
    val TrackingNumber: String,
    val Translate: Translate
)