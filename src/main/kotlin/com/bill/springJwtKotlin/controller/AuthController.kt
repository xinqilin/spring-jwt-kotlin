package com.bill.springJwtKotlin.controller

import com.bill.springJwtKotlin.dto.JwtAuthResponse
import com.bill.springJwtKotlin.dto.LoginDto
import com.bill.springJwtKotlin.service.AuthService
import lombok.extern.log4j.Log4j2
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Bill.Lin 2024/1/18
 */
@Log4j2
@RequestMapping("/api/auth")
@RestController
class AuthController(
        private val authService: AuthService? = null
) {

    // Build Login REST API
    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<JwtAuthResponse> {
        println("loginDto: $loginDto")
        val token = authService?.login(loginDto)
        val jwtAuthResponse = JwtAuthResponse(token!!)
        return ResponseEntity(jwtAuthResponse, HttpStatus.OK)
    }
}