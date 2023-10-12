package com.techbyfloig.cubannotary.notary.model.ups.response.oauth.token

import com.techbyfloig.cubannotary.notary.model.ups.entities.UpsOauthKeys

data class TokenResp(
    val accessToken: String,
    val clientId: String,
    val expiresIn: String,
    val issuedAt: String,
    val status: String,
    val tokenType: String
)