package com.example.samu.manager.config.servicies

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import com.example.samu.manager.repositories.UsuarioRepository
import org.springframework.security.core.userdetails.User


@Service
class UserSecurityService(private val usuarioRepository: UsuarioRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val usuario = usuarioRepository.findByNombreUsuario(username)
            ?: throw UsernameNotFoundException("Usuario no encontrado: $username")

        return User.withUsername(usuario.nombreUsuario)
            .password(usuario.contrasena)
            .roles(usuario.rol?.name ?: "USUARIO_NORMAL")
            .build()
    }
}