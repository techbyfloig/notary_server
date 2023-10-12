package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.HairColor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "hair_color")
data class HairColor(
    @Id @GeneratedValue val id: Long,
    val color: String
) {
    fun toDto(): HairColor {
        return HairColor(
            color = color
        )
    }
}