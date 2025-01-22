package com.example.samu.manager.config.services.dto

import com.example.samu.manager.models.Estado
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class MaquinaDTO(
    @field:NotBlank(message = "El modelo no puede estar vacío")
    val modelo: String,

    @field:NotBlank(message = "El nombre no puede estar vacío")
    val nombre: String,

    @field:NotBlank(message = "La ubicación no puede estar vacía")
    val ubicacion: String,

    @field:NotNull(message = "El estado es obligatorio")
    val estado: Estado,

    @field:NotNull(message = "El ID del empleado encargado es obligatorio")
    val empleadoEncargadoId: Long,

    @field:NotNull(message = "El ID del usuario es obligatorio")
    val usuarioId: Long
)
