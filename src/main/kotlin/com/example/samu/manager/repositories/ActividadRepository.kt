package com.example.samu.manager.repositories

import com.example.samu.manager.models.Actividad
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ActividadRepository : JpaRepository<Actividad, Long> {
    fun findByTrabajoId(trabajoId: Long): List<Actividad>
    fun findAllByFechaTrabajadaBetween(inicio: LocalDate, fin: LocalDate): List<Actividad>
    fun findByTrabajoIdAndTipoTrabajoId(trabajoId: Long, tipoTrabajoId: Long): List<Actividad>

}