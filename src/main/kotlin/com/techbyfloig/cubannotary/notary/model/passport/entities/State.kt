package com.techbyfloig.cubannotary.notary.model.passport.entities

import com.techbyfloig.cubannotary.notary.model.passport.response.State
import com.techbyfloig.cubannotary.notary.model.pdf.constantTables.USState
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "state")
data class State(
    @Id @GeneratedValue val id: Long,
    val code: String,
){
    fun toDto(): State {
        return State(
            code = code
        )
    }
}