package com.bill.springJwtKotlin.dao

import com.bill.springJwtKotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 * @author Bill.Lin 2024/1/17
 */
interface UserDao : JpaRepository<User, Long> {
    fun findByUsernameOrEmail(username: String?, email: String?): Optional<User>
}