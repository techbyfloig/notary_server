package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.Sex
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "sex")
data class Sex(
    @Id @GeneratedValue val id: Long,
    val sex: String
){
    fun toDto(): Sex {
        return Sex(
            sex = sex
        )
    }
}