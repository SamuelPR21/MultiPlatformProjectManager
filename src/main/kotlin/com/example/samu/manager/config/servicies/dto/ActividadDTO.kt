package com.example.samu.manager.config.servicies.dto


import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDate

data class ActividadDTO(
    @field:NotNull(message = "La fecha trabajada no puede estar vacía")
    val fechaTrabajada: LocalDate,

    @field:NotNull(message = "La cantidad de unidades no puede estar vacía")
    @field:Positive(message = "La cantidad de unidades debe ser un valor positivo")
    val cantidadUnidades: Int,

    @field:NotNull(message = "El ID del tipo de trabajo no puede estar vacío")
    val tipoTrabajoId: Long,

    @field:NotNull(message = "El ID del trabajo no puede estar vacío")
    val trabajoId: Long
)