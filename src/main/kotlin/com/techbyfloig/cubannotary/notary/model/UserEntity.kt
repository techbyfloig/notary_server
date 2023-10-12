package com.techbyfloig.cubannotary.notary.model

import javax.persistence.*


@Entity
@Table( name = "users")
data class UserEntity(
    val username: String,
    val password: String,
    val fullName: String,
    val enabled: Boolean,
    val provider: String,
    var audience: String = "",
    val pictureUrl: String = "",
    @Id @GeneratedValue var id: Long = 0,

//    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
//    @JoinTable(name= "users_roles", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
//    var roles: List<Roles>  = arrayListOf()
)