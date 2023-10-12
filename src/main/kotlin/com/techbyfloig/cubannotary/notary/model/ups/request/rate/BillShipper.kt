package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class BillShipper(
    @SerializedName("AccountNumber")
    val accountNumber: String
)