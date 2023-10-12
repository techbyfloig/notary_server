package com.techbyfloig.cubannotary.notary.repo

import com.techbyfloig.cubannotary.notary.model.JwtSecretKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface JwtKeyRepo : JpaRepository<JwtSecretKey, Long> {

    fun getKeysByAudience(audience: String): List<JwtSecretKey>

}