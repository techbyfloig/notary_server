package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.EyesColor
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.EyeColor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "eyes_color")
data class EyesColor(
    @Id @GeneratedValue val id: Long,
    val color: String
) {
    fun toDto(): EyesColor {
          return EyesColor(
                color = color
            )
    }
}
