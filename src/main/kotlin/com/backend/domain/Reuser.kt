package com.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.persistence.CascadeType.*
import jakarta.persistence.GenerationType.*

@Entity
class Reuser(
    username: String,
    password: String,
    role: String,
    NAME: String,
    @JsonIgnore
    @OneToMany(cascade = [(ALL)], mappedBy = "reuser")
    var readdresses: MutableList<Readdress> = mutableListOf()
) {
    @Id @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @Column(nullable = false, unique = true)
    var username: String = username

    @JsonIgnore
    @Column(nullable = false)
    var password: String = password

    @Column(nullable = false)
    var role: String = role

    var NAME: String = NAME

}