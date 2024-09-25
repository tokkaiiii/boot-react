package com.backend.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtService {
    val EXPIRATION_TIME : Long = 86400000 //1day ms로 변환
    val TOKEN_PREFIX = "Bearer "
    val KEY_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun generateToken(username: String): String { //토큰 리턴
        return Jwts.builder().setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(KEY_SECRET)
            .compact()
    }

    fun getAuthUser(request: HttpServletRequest): String {
        return when (val token = request.getHeader(HttpHeaders.AUTHORIZATION)){
            null ->  ""
            else -> Jwts.parserBuilder()
                .setSigningKey(KEY_SECRET)
                .build()
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .body
                .subject
        }
    }
}