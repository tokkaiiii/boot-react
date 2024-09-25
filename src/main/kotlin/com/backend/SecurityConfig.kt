package com.backend

import com.backend.comp.AuthEntryPoint
import com.backend.comp.AuthenticationFilter
import com.backend.service.JwtService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@Slf4j
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
) {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(BCryptPasswordEncoder())
    }


    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors { it.configurationSource(corsConfigurationSource()) }
        http.csrf { it.disable() } // 주의
            .authorizeHttpRequests {
                it.requestMatchers("/login").permitAll()
                    .anyRequest().authenticated() // POST /login 이외의 모든 요청 보호됨
            }
            .exceptionHandling { it.authenticationEntryPoint(AuthEntryPoint()) }
        http.addFilterBefore(
            AuthenticationFilter(jwtService),
            UsernamePasswordAuthenticationFilter::class.java
        )
        return http.build()
    }

    @Bean
    fun getAuthenticationManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder::class.java)
        // authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider())
        return authenticationManagerBuilder.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowCredentials = true

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}