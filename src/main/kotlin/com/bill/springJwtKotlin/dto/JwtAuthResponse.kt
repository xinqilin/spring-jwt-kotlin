package com.bill.springJwtKotlin.dto

import lombok.NoArgsConstructor

/**
 * @author Bill.Lin 2024/1/18
 */
@NoArgsConstructor
data class JwtAuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)
