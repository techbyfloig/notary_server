package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.PassportInfoDto
import javax.persistence.*


@Entity
@Table(name = "passport_info")
data class PassportInfo(
    @Id @GeneratedValue val id: Long,
    val number: String,
    @Column(name = "emission_date")
    val emissionDate: Long,
    @Column(name = "emission_place")
    val emissionPlace: String,
    @Column(name = "expiration_date")
    val expirationDate: Long,
){
    fun toDto(): PassportInfoDto {
        return PassportInfoDto(
            number = number,
            emissionDate = emissionDate,
            emissionPlace = emissionPlace,
            expirationDate = expirationDate,
        )
    }
}