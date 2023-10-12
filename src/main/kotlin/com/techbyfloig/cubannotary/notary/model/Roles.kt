package com.techbyfloig.cubannotary.notary.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table( name = "roles")
data class Roles(
    @Id @GeneratedValue val id: Int = 0,
     val name: String = "User"
)