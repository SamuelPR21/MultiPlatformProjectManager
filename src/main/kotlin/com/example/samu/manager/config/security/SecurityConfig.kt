package com.example.samu.manager.config.security

import com.example.samu.manager.config.security.jwt.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfig (private val jwtFilter: JwtFilter) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf{ it.disable() }
            .cors{}
            .authorizeRequests{
                it.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()

                    .requestMatchers(HttpMethod.GET, "/cliente/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.POST, "/cliente/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.PATCH, "/cliente/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.DELETE, "/cliente/**").hasRole("USUARIO_NORMAL")

                    .requestMatchers(HttpMethod.GET, "/empleado/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.POST, "/empleado/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.PATCH, "/empleado/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.DELETE, "/empleado/**").hasRole("USUARIO_NORMAL")

                    .requestMatchers(HttpMethod.GET, "/maquina/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.POST, "/maquina/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.PATCH, "/maquina/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.DELETE, "/maquina/**").hasRole("USUARIO_NORMAL")

                    .requestMatchers(HttpMethod.GET, "/pago/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.POST, "/pago/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.PATCH, "/pago/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.DELETE, "/pago/**").hasRole("USUARIO_NORMAL")

                    .requestMatchers(HttpMethod.GET, "/TTrabajo/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.POST, "/TTrabajo/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.PATCH, "/TTrabajo/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.DELETE, "/TTrabajo/**").hasRole("USUARIO_NORMAL")

                    .requestMatchers(HttpMethod.GET, "/job/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.POST, "/job/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.PATCH, "/job/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.DELETE, "/job/**").hasRole("USUARIO_NORMAL")

                    .requestMatchers(HttpMethod.POST, "/actvidad/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.GET, "/actvidad/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.DELETE, "/actvidad/**").hasRole("USUARIO_NORMAL")
                    .requestMatchers(HttpMethod.PATCH, "/actvidad/**").hasRole("USUARIO_NORMAL")

                    .requestMatchers(HttpMethod.GET, "/users/**").hasRole("SUPERUSUARIO")
                    .requestMatchers(HttpMethod.POST, "/users/**").hasRole("SUPERUSUARIO")
                    .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("SUPERUSUARIO")
                    .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("SUPERUSUARIO")

                    .anyRequest()
                    .authenticated()
            }

            .addFilterBefore(jwtFilter, BasicAuthenticationFilter::class.java)


        return http.build()
    }

    @Bean
    fun PasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager {
        return configuration.authenticationManager
    }

}