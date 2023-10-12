package com.techbyfloig.cubannotary.android.network.models.response.oauth.generatetoken

data class OauthTokenResp(
    val access_token: String,
    val client_id: String,
    val expires_in: String,
    val issued_at: String,
    val refresh_count: String,
    val refresh_token: String,
    val refresh_token_expires_in: String,
    val refresh_token_issued_at: String,
    val refresh_token_status: String,
    val scope: String,
    val status: String,
    val token_type: String
)