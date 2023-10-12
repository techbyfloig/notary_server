package com.techbyfloig.cubannotary.notary.repo

import com.techbyfloig.cubannotary.notary.model.ups.entities.UpsOauthKeys
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UpsOAuthDb: JpaRepository<UpsOauthKeys, Int> {

}
