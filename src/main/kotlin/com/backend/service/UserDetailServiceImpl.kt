package com.backend.service

import com.backend.repository.ReuserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(private val reuserRepository: ReuserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val reuser = reuserRepository.findByUsername(username!!) ?: throw UsernameNotFoundException(
            "User not found"
        )
        return User.withUsername(reuser.username)
            .password(reuser.password)
            .roles("USER")
            .build()
    }
}