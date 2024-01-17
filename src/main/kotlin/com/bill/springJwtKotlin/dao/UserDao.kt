package com.bill.springJwtKotlin.dao

import com.bill.springJwtKotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Bill.Lin 2024/1/17
 */
interface UserDao : JpaRepository<User, Long> {
}