package com.bill.springJwtKotlin.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor


/**
 * @author Bill.Lin 2024/1/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null
    private val name: String? = null

    @Column(nullable = false, unique = true)
    private val username: String? = null

    @Column(nullable = false, unique = true)
    private val email: String? = null

    @Column(nullable = false)
    private val password: String? = null

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(name = "users_roles", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
    private val roles: Set<Role>? = null
}