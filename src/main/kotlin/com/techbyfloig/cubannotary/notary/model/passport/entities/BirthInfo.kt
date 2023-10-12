package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.BirthInfoDto
import javax.persistence.*

@Entity
@Table(name = "birth_info")
data class BirthInfo(
    @Id @GeneratedValue val id: Long,
    val country: String,
    val city: String,
    val state: String,
    val tomo: String,
    val folio: String,
    val register: String,
    val dateOfBirth: Long
){
    fun toDto(): BirthInfoDto {
        return BirthInfoDto(
            country = country,
            city = city,
            state = state,
            tomo = tomo,
            folio = folio,
            register = register,
            dateOfBirth = dateOfBirth
        )
    }
}