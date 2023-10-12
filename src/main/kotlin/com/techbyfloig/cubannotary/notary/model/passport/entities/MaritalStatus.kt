package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.MaritalStatus
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "marital_status")
data class MaritalStatus(
    @Id @GeneratedValue val id: Long,
    val status: String
){
    fun toDto(): MaritalStatus {
        return MaritalStatus(
            status = status
        )
    }
}