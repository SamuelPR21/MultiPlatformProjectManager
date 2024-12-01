package com.example.samu.manager.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf{ it.disable() }
            .authorizeRequests()

            .requestMatchers(HttpMethod.GET, "/cliente/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/cliente/**").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/cliente/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/cliente/**").permitAll()

            .requestMatchers(HttpMethod.GET, "/empleado/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/empleado/**").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/empleado/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/empleado/**").permitAll()

            .requestMatchers(HttpMethod.GET, "/maquina/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/maquina/**").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/maquina/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/maquina/**").permitAll()

            .requestMatchers(HttpMethod.GET, "/pago/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/pago/**").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/pago/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/pago/**").permitAll()

            .requestMatchers(HttpMethod.GET, "/TTrabajo/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/TTrabajo/**").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/TTrabajo/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/TTrabajo/**").permitAll()

            .requestMatchers(HttpMethod.GET, "/job/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/job/**").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/job/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/job/**").permitAll()

            .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/users/**").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/users/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/users/**").permitAll()

            .anyRequest()
            .authenticated()
            .and()
            .httpBasic(Customizer.withDefaults());

        return http.build()
    }

    @Bean
    fun PasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}