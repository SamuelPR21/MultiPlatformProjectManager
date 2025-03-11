package com.example.samu.manager.config.servicies

import com.example.samu.manager.models.Trabajo
import com.example.samu.manager.repositories.TrabajoRepository
import org.springframework.stereotype.Service

@Service
class TrabajoService (private val trabajoRepository: TrabajoRepository){
    fun obtenerTrabajosPorUsuario(usuarioId: Long): List<Trabajo>{
        return  trabajoRepository.findByUsuarioId(usuarioId)
        }
    }
