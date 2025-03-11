package com.example.samu.manager.repositories

import com.example.samu.manager.models.Trabajo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TrabajoRepository: JpaRepository<Trabajo, Long> {

    @Query("SELECT t FROM Trabajo t WHERE t.usuario.id = :usuarioId")
    fun findByUsuarioId(@Param("usuarioId") usuarioId: Long): List<Trabajo>

}