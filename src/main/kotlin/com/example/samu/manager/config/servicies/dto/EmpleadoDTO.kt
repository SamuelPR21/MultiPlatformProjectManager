package com.example.samu.manager.config.servicies.dto

import com.example.samu.manager.models.Nivel
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class EmpleadoDTO (
    @field:NotBlank(message = "El nombre no puede estar vacío")
    val nombreCompleto: String,

    @field: NotBlank(message = "El número de contacto no puede estar vacío")
    val numeroContacto: String,

    @field: NotBlank(message = "La dirección no puede estar vacía")
    val direccion: String,

    @field: NotBlank(message = "La fecha de nacimiento no puede estar vacía")
    val fechaNacimiento: java.time.LocalDate,

    @field: NotNull(message = "El número de identificación es obligatorio")
    val numeroIdentificacion: String,

    @field: NotNull(message = "El cargo es obligatorio")
    val cargo: String,

    @field: NotNull(message = "El nivel es obligatorio")
    val nivel: Nivel,

    @field: NotNull(message = "El ID del usuario es obligatorio")
    val usuarioId: Long

)


