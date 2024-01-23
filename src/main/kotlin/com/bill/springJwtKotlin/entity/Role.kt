package com.bill.springJwtKotlin.entity

import jakarta.persistence.*


/**
 * @author Bill.Lin 2024/1/17
 */

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String? = null
)