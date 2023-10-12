package com.techbyfloig.cubannotary.notary.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "jwt_secret_key")
data class JwtSecretKey(
    @Id val id: Long,
    val audience: String,
    var accessSecretKey: String,
    var refreshSecretKey: String
)
