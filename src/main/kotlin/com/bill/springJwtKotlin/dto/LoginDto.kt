package com.bill.springJwtKotlin.dto

/**
 * @author Bill.Lin 2024/1/18
 */
data class LoginDto(
    val usernameOrEmail: String,
    val password: String
)