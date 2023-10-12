package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.CubaExitInfoDto
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "cuba_exit_info")
data class CubaExitInfo(
    @Id @GeneratedValue val id: Long,
    val type: String,
    @Column(name = "exit_date")
    val exitDate: Long
){
    fun toDto(): CubaExitInfoDto {
        return CubaExitInfoDto(
            type = type,
            exitDate = exitDate
        )
    }
}