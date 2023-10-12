package com.techbyfloig.cubannotary.notary.repo

import com.techbyfloig.cubannotary.notary.model.passport.entities.Passport
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PassportRepo : JpaRepository<Passport, Long> {
    @Query("SELECT p FROM Passport p WHERE p.userId.id = :userId")
    fun findByUserId(userId: Long): List<Passport>
}
