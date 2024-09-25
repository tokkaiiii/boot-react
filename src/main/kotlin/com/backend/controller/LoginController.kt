package com.backend.controller

import com.backend.domain.AccountCredentials
import com.backend.service.JwtService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/login")
    fun getToken(@RequestBody accountCredentials: AccountCredentials): ResponseEntity<Any>{
        val creds = UsernamePasswordAuthenticationToken(
            accountCredentials.username,
            accountCredentials.password
        )
        val authenticate = authenticationManager.authenticate(creds)
        val token = jwtService.generateToken(authenticate.name)
        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION,"Bearer${token}")
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization")
            .build()
    }

}