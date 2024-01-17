package com.bill.springJwtKotlin.entity

import jakarta.persistence.*


/**
 * @author Bill.Lin 2024/1/17
 */

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var name: String? = null,
        @Column(nullable = false, unique = true)
        var username: String? = null,
        @Column(nullable = false, unique = true)
        var email: String? = null,
        @Column(nullable = false)
        var password: String? = null,

        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
        )
        var roles: Set<Role>? = null
)
