package com.techbyfloig.cubannotary.notary.model.ups.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ups_oauth_keys")
data class UpsOauthKeys(
    @Id val id: Long,
    @Column(name = "client_id")
    var clientId: String,
    @Column(name = "client_key")
    var clientKey: String,
    @Column(name = "secret_key")
    var secretKey: String,
    @Column(name = "access_token")
    var accessToken: String,
    @Column(name = "token_type")
    var tokenType: String,
    @Column(name = "expires_in")
    var expiresIn: String,
    @Column(name = "issued_at")
    var issuedAt: String,
    var status: String
)
