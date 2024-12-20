package com.example.samu.manager.repositories

import com.example.samu.manager.models.Usuarios
import org.springframework.data.repository.CrudRepository

interface  UsuarioReposittory: CrudRepository<Usuarios, Long> {
    fun findByNombreUsuario(nombreUsuario: String): Usuarios?
}