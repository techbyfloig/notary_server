package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.AddressInfoDto
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.USState
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "address_info")
data class AddressInfo(
    @Id @GeneratedValue val id: Long,
    val street: String,
    val apt: String,
    val city: String,
    val zip: String,
    @OneToOne
    @JoinColumn(name = "state_id") //foreign key name
    val stateId: State,
){
    fun toDto(): AddressInfoDto {
        return AddressInfoDto(
            street = street,
            apt = apt,
            city = city,
            zip = zip,
            state = stateId.toDto()
        )
    }
}