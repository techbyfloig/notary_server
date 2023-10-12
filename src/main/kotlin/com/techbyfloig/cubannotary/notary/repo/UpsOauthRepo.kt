package com.techbyfloig.cubannotary.notary.repo

import com.techbyfloig.cubannotary.notary.model.ups.response.oauth.token.TokenResp


class UpsOauthRepo(
    private val upsOAuthDb: UpsOAuthDb,
    private val oauthNetwork: UpsOauthNetwork
) {

    fun getToken(): Result<String> {
        //get entity from db and check if it is expired
        val dbTokenEntity = upsOAuthDb.findAll().firstOrNull() ?: return Result.failure(Exception("No token found"))

        if(dbTokenEntity.expiresIn.toLong() < System.currentTimeMillis()){
            // if expired call network and save to db
            val networkTokenEntity = oauthNetwork.getTokenEntity()
            if(networkTokenEntity.isSuccess){
                val tokenResp = networkTokenEntity.getOrNull() ?: return Result.failure(Exception("Error getting token"))
                dbTokenEntity.apply {
                    accessToken = tokenResp.accessToken
                    clientId = tokenResp.clientId
                    expiresIn = tokenResp.expiresIn
                    issuedAt = tokenResp.issuedAt
                    status = tokenResp.status
                    tokenType = tokenResp.tokenType
                }
                upsOAuthDb.deleteAll()
                upsOAuthDb.save(dbTokenEntity)
                return Result.success(dbTokenEntity.accessToken)
            }
        } else {
            // if not expired return token
            return Result.success(dbTokenEntity.accessToken)
        }

        return Result.failure(Exception("Error getting token"))
    }

}