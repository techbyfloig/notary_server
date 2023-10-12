package com.techbyfloig.cubannotary.notary.model.passport.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import com.techbyfloig.cubannotary.notary.model.passport.response.ServiceType

@Entity
@Table(name = "service_type")
data class ServiceType(
    @Id @GeneratedValue val id: Long,
    val name: String
) {
    fun toDto(): ServiceType {
        return ServiceType(
            name = name
        )
    }
}