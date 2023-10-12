package com.techbyfloig.cubannotary.android.network.models.request.label

data class LabelSpecification(
    val HTTPUserAgent: String,
    val LabelImageFormat: LabelImageFormat,
    val LabelStockSize: LabelStockSize
)