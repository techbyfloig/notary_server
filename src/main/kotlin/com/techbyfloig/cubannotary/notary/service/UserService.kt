package com.techbyfloig.cubannotary.notary.service

import com.techbyfloig.cubannotary.notary.model.*
import com.techbyfloig.cubannotary.notary.model.UserEntity
import com.techbyfloig.cubannotary.notary.model.UserServiceDetails
import com.techbyfloig.cubannotary.notary.repo.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService : UserDetailsService {

    private val userAuthRepo: UserRepo

    @Autowired
    constructor(
        userAuthRepo: UserRepo
    ) {
        this.userAuthRepo = userAuthRepo
    }

    override fun loadUserByUsername(userName: String): UserDetails {
        val user = userAuthRepo.findByUsername(userName).orElseThrow {
            UsernameNotFoundException("User name with $userName not found")
        }
        return UserServiceDetails(user)
    }

    private fun mapToAuthorities(roles: List<Roles>): Collection<GrantedAuthority> {
        return roles.map { role ->
            SimpleGrantedAuthority(role.name)
        }
    }

    fun createNewUser(user: UserEntity): Result<String> {
        return try {
            userAuthRepo.save(user)
            Result.success("Success")
        } catch (ex: IllegalArgumentException) {
            Result.failure(ex)
        }
    }


}