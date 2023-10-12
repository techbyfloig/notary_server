package com.techbyfloig.cubannotary.android.network.models.response.shippingtime

data class ValidationList(
    val destinationAmbiguous: Boolean,
    val invalidFieldList: List<String>,
    val invalidFieldListCodes: List<String>,
    val originAmbiguous: Boolean
)