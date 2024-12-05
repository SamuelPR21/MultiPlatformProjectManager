package com.example.samu.manager.controller

import com.example.samu.manager.config.security.jwt.JwtUtil
import com.example.samu.manager.config.services.dto.LoginDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {
    val authenticationManager: AuthenticationManager
    val JwtUtil: JwtUtil

    @Autowired
    constructor(authenticationManager: AuthenticationManager) {
        this.authenticationManager = authenticationManager
        this.JwtUtil = JwtUtil()
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<Void> {
        val login = UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
        val authentication = authenticationManager.authenticate(login)

        // Puedes agregar el manejo de la respuesta aquí, por ejemplo, generando el token JWT y devolviendo una respuesta
        val jwt = this.JwtUtil.create(loginDto.username)

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build()
    }


}