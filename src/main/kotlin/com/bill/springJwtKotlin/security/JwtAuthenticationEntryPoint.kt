package com.bill.springJwtKotlin.security

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.log4j.Log4j2
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException

/**
 * @author Bill.Lin 2024/1/17
 * AuthenticationEntryPoint is used by ExceptionTranslationFilter to commence an authentication scheme.
 * It is the entry point to check if a user is authenticated and logs the person in or throws an exception (unauthorized).
 *
 */
@Log4j2
@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(
            request: HttpServletRequest?,
            response: HttpServletResponse?,
            authException: AuthenticationException?) {
        response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException?.message)
    }
}