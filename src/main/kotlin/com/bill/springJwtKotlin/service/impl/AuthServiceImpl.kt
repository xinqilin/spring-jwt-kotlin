package com.bill.springJwtKotlin.service.impl

import com.bill.springJwtKotlin.dto.LoginDto
import com.bill.springJwtKotlin.security.JwtTokenProvider
import com.bill.springJwtKotlin.service.AuthService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service


/**
 * @author Bill.Lin 2024/1/18
 */
@Service
class AuthServiceImpl(
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider
) : AuthService {
    override fun login(loginDto: LoginDto?): String {
        val authentication: Authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                loginDto?.usernameOrEmail,
                loginDto?.password
        ))

        SecurityContextHolder.getContext().authentication = authentication

        val token: String = jwtTokenProvider.generateToken(authentication)

        return token
    }
}