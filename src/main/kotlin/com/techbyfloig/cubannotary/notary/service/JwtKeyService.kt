package com.techbyfloig.cubannotary.notary.service

import com.techbyfloig.cubannotary.notary.model.JwtSecretKey
import com.techbyfloig.cubannotary.notary.repo.JwtKeyRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JwtKeyService {

    private val jwtKeyRepo: JwtKeyRepo

    @Autowired
    constructor(
        jwtKeyRepo: JwtKeyRepo
    ){
        this.jwtKeyRepo = jwtKeyRepo
    }

    fun addRefreshKey(userId: Long, updatedKey: String): JwtSecretKey? {
        val existingUser = jwtKeyRepo.findById(userId)
        if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            jwtKeyRepo.delete(userToUpdate)
            userToUpdate.refreshSecretKey = updatedKey
            // Update other fields as needed
            return jwtKeyRepo.save(userToUpdate)
        }
        return null
    }

    fun addAccessKey(userId: Long, updatedKey: String): JwtSecretKey? {
        val existingUser = jwtKeyRepo.findById(userId)
        if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            jwtKeyRepo.delete(userToUpdate)
            userToUpdate.accessSecretKey = updatedKey
            // Update other fields as needed
            return jwtKeyRepo.save(userToUpdate)
        }
        return null
    }





}