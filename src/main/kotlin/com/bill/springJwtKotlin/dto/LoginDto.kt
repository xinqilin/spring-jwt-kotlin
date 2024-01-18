package com.bill.springJwtKotlin.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

/**
 * @author Bill.Lin 2024/1/18
 */
data class LoginDto(
        val usernameOrEmail: String,
        val password: String
)