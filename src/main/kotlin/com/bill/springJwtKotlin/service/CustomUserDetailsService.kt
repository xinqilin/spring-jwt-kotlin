package com.bill.springJwtKotlin.service

import com.bill.springJwtKotlin.dao.UserDao
import com.bill.springJwtKotlin.entity.User
import lombok.RequiredArgsConstructor
import lombok.extern.log4j.Log4j2
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors


/**
 * @author Bill.Lin 2024/1/18
 */
@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
class CustomUserDetailsService(
        private val userDao: UserDao
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(usernameOrEmail: String?): UserDetails {
        val user: User = userDao.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow { UsernameNotFoundException("User not exists by Username or Email") }

        val authorities: Set<GrantedAuthority> = user.roles!!.map { role -> SimpleGrantedAuthority(role.name) }
                .toSet()

        return org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.password,
                authorities
        )
    }
}