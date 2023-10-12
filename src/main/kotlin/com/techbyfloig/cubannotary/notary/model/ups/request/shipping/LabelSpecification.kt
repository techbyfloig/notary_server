package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class LabelSpecification(
    val HTTPUserAgent: String,
    val LabelImageFormat: LabelImageFormat
)