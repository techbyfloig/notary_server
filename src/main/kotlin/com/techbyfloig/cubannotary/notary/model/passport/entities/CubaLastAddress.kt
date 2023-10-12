package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.CubaLastAddressDto
import javax.persistence.*

@Entity
@Table(name = "cuba_last_address")
data class CubaLastAddress(
    @Id @GeneratedValue val id: Long,
    val address: String,
    @Column(name = "from_date")
    val fromDate: Long,
    @Column(name = "to_date")
    val toDate: Long
){
    fun toDto(): CubaLastAddressDto {
        return CubaLastAddressDto(
            address = address,
            fromDate = fromDate,
            toDate = toDate
        )
    }
}