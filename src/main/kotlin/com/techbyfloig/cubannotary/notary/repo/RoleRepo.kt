package com.techbyfloig.cubannotary.notary.repo

import com.techbyfloig.cubannotary.notary.model.Roles
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepo: JpaRepository<Roles, Int> {

    fun findByName(name: String): Optional<Roles>

}