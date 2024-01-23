package com.bill.springJwtKotlin.config

import com.bill.springJwtKotlin.security.JwtAuthenticationEntryPoint
import com.bill.springJwtKotlin.security.JwtAuthenticationFilter
import lombok.AllArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * @author Bill.Lin 2024/1/18
 */
@Configuration
@EnableMethodSecurity
@AllArgsConstructor
class SpringSecurityConfig(
    private val userDetailsService: UserDetailsService,
    private val authenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val authenticationFilter: JwtAuthenticationFilter
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { authorize ->
//                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
                authorize.requestMatchers("/api/auth/**").permitAll()
                authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                authorize.anyRequest().authenticated()
            }.httpBasic(Customizer.withDefaults())

        http.exceptionHandling { exception: ExceptionHandlingConfigurer<HttpSecurity?> ->
            exception
                .authenticationEntryPoint(authenticationEntryPoint)
        }

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }

//    @Bean
    //    public UserDetailsService userDetailsService(){
    //
    //        UserDetails ramesh = User.builder()
    //                .username("ramesh")
    //                .password(passwordEncoder().encode("password"))
    //                .roles("USER")
    //                .build();
    //
    //        UserDetails admin = User.builder()
    //                .username("admin")
    //                .password(passwordEncoder().encode("admin"))
    //                .roles("ADMIN")
    //                .build();
    //
    //        return new InMemoryUserDetailsManager(ramesh, admin);
    //    }


}