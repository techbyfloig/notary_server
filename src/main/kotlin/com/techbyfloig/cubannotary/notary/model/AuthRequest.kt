package com.techbyfloig.cubannotary.notary.model


data class AuthReq(
    val username: String,
    val password: String,
    val name: String,
    val provider: String,
    val googleToken: String?,
){
    fun toUserEntity(): UserEntity {
        return UserEntity(
            username = username,
            password = password,
            fullName = name,
            enabled = true,
            provider = provider
        )
    }
}
