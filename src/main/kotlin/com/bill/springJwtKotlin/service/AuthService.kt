package com.bill.springJwtKotlin.service

import com.bill.springJwtKotlin.dto.LoginDto


/**
 * @author Bill.Lin 2024/1/18
 */
interface AuthService {
    fun login(loginDto: LoginDto?): String?
}