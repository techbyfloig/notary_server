package com.techbyfloig.cubannotary.notary.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserServiceDetails(
    val users: UserEntity
) : UserDetails {

    override fun getUsername(): String = users.username

    override fun getPassword(): String = users.password

    override fun getAuthorities(): Collection<GrantedAuthority> = mutableListOf()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = users.enabled

}