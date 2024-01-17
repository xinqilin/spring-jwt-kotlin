package com.bill.springJwtKotlin.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException


/**
 * @author Bill.Lin 2024/1/18
 * Let's create a JwtAuthenticationFilter class in a Spring Boot application
 * that intercepts incoming HTTP requests and validates JWT tokens that are included in the Authorization header.
 * If the token is valid, the filter sets the current user's authentication in the SecurityContext.
 */
class JwtAuthenticationFilter(
        jwtTokenProvider: JwtTokenProvider?,
        userDetailsService: UserDetailsService?
) : OncePerRequestFilter() {

    private var jwtTokenProvider: JwtTokenProvider? = null
    private var userDetailsService: UserDetailsService? = null


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        // Get JWT token from HTTP request
        val token = getTokenFromRequest(request)

        // Validate Token
        if (StringUtils.hasText(token) && jwtTokenProvider!!.validateToken(token)) {
            // get username from token
            val username = jwtTokenProvider!!.getUsername(token)

            val userDetails = userDetailsService!!.loadUserByUsername(username)

            val authenticationToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
            )

            authenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

            SecurityContextHolder.getContext().authentication = authenticationToken
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length)
        }

        return null
    }
}