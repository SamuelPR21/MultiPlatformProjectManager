package com.example.samu.manager.config.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@Component
class JwtFilter (private val jwtUtil: JwtUtil, private val userDetailsService: UserDetailsService ): OncePerRequestFilter() {


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.requestURI
        //Exluir rutas abiertas
        if(path.startsWith("/auth/login") || path.startsWith("/auth/register")){
            filterChain.doFilter(request, response)
            return
        }

        // Validar que sea un header de autorización válido
        val authorizationHeader = request.getHeader("Authorization") // HttpHeaders.AUTHORIZATION corregido a "Authorization"

        if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response)
            return
        }

        //Validar el token
        val token = authorizationHeader.split(" ")[1].trim()
        if (!jwtUtil.validate(token)) {
            filterChain.doFilter(request, response)
            return
        }

        //Caragar el usuario del UserDatailsService
        val username = JwtUtil().getUsername(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)

        //Cargar el usuario ene l contexto de seguridad
        val authentication = UsernamePasswordAuthenticationToken(
            userDetails.username,
            null,  // La contraseña no es necesaria en este contexto
            userDetails.authorities  // Esto carga las autoridades/roles del usuario
        )
        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }
}
