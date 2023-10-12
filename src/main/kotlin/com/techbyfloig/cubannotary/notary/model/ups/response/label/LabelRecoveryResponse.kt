package com.techbyfloig.cubannotary.android.network.models.response.label

data class LabelRecoveryResponse(
    val LabelResults: LabelResult,
    val Response: Response,
    val ShipmentIdentificationNumber: String,
)