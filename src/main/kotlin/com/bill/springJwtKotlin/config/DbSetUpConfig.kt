package com.bill.springJwtKotlin.config

import com.bill.springJwtKotlin.dao.RoleDao
import com.bill.springJwtKotlin.dao.UserDao
import com.bill.springJwtKotlin.entity.Role
import com.bill.springJwtKotlin.entity.User
import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * @author Bill.Lin 2024/1/18
 */
@Component
class DbSetUpConfig(
    private val passwordEncoder: PasswordEncoder,
    private val userDao: UserDao,
    private val roleDao: RoleDao
) {

    @PostConstruct
    fun init() {
        val role1 = roleDao.findByName("ROLE_ADMIN").orElse(Role(1, "ROLE_ADMIN"))
        val role2 = roleDao.findByName("ROLE_ADMIN").orElse(Role(2, "ROLE_USER"))
        roleDao.save(role1)
        roleDao.save(role2)
        val user1 = userDao.findByUsernameOrEmail("bill", "bill@gmail.com")
            .orElse(User(1, "bill", "bill", "bill@gmail.com", passwordEncoder.encode("bill"), setOf(role2)))
        val user2 = userDao.findByUsernameOrEmail("david", "david@gmail.com")
            .orElse(User(2, "david", "david", "david@gmail.com", passwordEncoder.encode("david"), setOf(role1)))
        userDao.save(user1)
        userDao.save(user2)
    }
}