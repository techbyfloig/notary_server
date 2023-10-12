package com.techbyfloig.cubannotary.notary.repo

import com.techbyfloig.cubannotary.notary.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo: JpaRepository<UserEntity, Long> {

    fun findByUsername(username: String): Optional<UserEntity>

    fun existsByUsername(username: String): Boolean
}

