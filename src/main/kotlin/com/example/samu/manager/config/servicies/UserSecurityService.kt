package com.example.samu.manager.config.servicies

import com.example.samu.manager.models.Usuarios
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import com.example.samu.manager.repositories.UsuarioReposittory
import org.springframework.security.core.userdetails.User


@Service
class UserSecurityService(private val usuarioRepository: UsuarioReposittory):
    UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw UsernameNotFoundException("El nombre de usuario no puede ser nulo")

        val usuario = usuarioRepository.findByNombreUsuario(username)
            ?: throw UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: $username")



        return User.builder()
            .username(usuario.nombreUsuario)
            .password(usuario.contrasena)
            .roles(usuario.rol?.name)
            .build()
        //UserDetailsImpl(usuario) // Debes implementar `UserDetailsImpl`
    }
}
