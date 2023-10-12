package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.SkinColor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "skin_color")
data class SkinColor(
    @Id @GeneratedValue val id: Long,
    val color: String
) {
    fun toDto(): SkinColor {
        return SkinColor(
            color = color
        )
    }
}