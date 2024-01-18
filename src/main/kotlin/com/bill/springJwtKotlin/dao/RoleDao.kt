package com.bill.springJwtKotlin.dao

import com.bill.springJwtKotlin.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

/**
 * @author Bill.Lin 2024/1/17
 */
interface RoleDao : JpaRepository<Role, Long> {

    fun findByName(name: String): Optional<Role>
}
