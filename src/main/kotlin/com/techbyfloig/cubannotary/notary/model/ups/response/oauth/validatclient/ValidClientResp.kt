package com.techbyfloig.cubannotary.android.network.models.response.oauth.validatclient

data class ValidClientResp(
    val LassoRedirectURL: String,
    val result: String,
    val type: String
)