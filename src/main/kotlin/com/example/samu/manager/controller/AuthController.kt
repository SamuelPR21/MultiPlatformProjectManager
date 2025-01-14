package com.example.samu.manager.controller

import com.example.samu.manager.config.security.jwt.JwtUtil
import com.example.samu.manager.config.services.dto.LoginDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    val authenticationManager: AuthenticationManager
    val jwtUtil: JwtUtil

    @Autowired
    constructor(authenticationManager: AuthenticationManager) {
        this.authenticationManager = authenticationManager
        this.jwtUtil = JwtUtil()
    }

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): ResponseEntity<Any> {
        return try {
            // Intentamos autenticar al usuario
            val login = UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
            val authentication = authenticationManager.authenticate(login)

            // Si la autenticación es exitosa, generamos el token JWT
            val jwt = this.jwtUtil.create(loginDto.username)

            // Devolvemos una respuesta exitosa con el token
            ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build()

        } catch (ex: BadCredentialsException) {
            // Capturamos errores de credenciales incorrectas
            ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(HttpStatus.UNAUTHORIZED.reasonPhrase)

        } catch (ex: Exception) {
            // Capturamos otros errores inesperados
            ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ha ocurrido un error al intentar iniciar sesión. Por favor, inténtalo de nuevo más tarde.")
        }
    }

    @DeleteMapping("/logout")
    fun logout(): ResponseEntity<Void> {
        // Puedes agregar el manejo de la respuesta aquí, por ejemplo, invalidando el token JWT y devolviendo una respuesta
        return ResponseEntity.ok().build()
    }
}
