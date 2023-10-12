package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.CubaReferenceDto
import javax.persistence.*

@Entity
@Table(name = "cuba_reference")
data class CubaReference(
    @Id @GeneratedValue val id: Long,
    @Column(name = "person_full_name")
    val personFullName: String,
    @Column(name = "person_phone")
    val personPhone: String,
    val address: String,
){
    fun toDto(): CubaReferenceDto {
        return CubaReferenceDto(
            personFullName = personFullName,
            personPhone = personPhone,
            address = address,
        )
    }
}