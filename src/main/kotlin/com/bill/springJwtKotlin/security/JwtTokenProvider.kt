package com.bill.springJwtKotlin.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.crypto.SecretKey


/**
 * @author Bill.Lin 2024/1/18
 * Let's create a Utility class named JwtTokenProvider which provides methods for generating,
 * validating, and extracting information from JSON Web Tokens (JWTs) used for authentication
 * in a Spring Boot application.
 */
@Component
class JwtTokenProvider {

    @Value("\${app.jwt-secret}")
    private val jwtSecret: String? = null

    @Value("\${app-jwt-expiration-milliseconds}")
    private val jwtExpirationDate: Long = 0

    // generate JWT token
    public fun generateToken(authentication: Authentication): String {
        val username = authentication.name
        val currentDate: Date = Date()
        val expireDate: Date = Date(currentDate.time + jwtExpirationDate)
        val token = Jwts.builder()
                .subject(username)
                .issuedAt(Date())
                .expiration(expireDate)
                .signWith(key())
                .compact()
        return token

    }

    private fun key(): Key {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))
    }

    // get username from JWT token
    fun getUsername(token: String?): String {
        return Jwts.parser()
                .verifyWith(key() as SecretKey)
                .build()
                .parseSignedClaims(token)
                .payload
                .subject
    }

    // validate JWT token
    fun validateToken(token: String?): Boolean {
        Jwts.parser()
                .verifyWith(key() as SecretKey)
                .build()
                .parse(token)
        return true
    }
}