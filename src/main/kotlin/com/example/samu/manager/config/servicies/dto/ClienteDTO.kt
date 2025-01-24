package com.example.samu.manager.config.servicies.dto

import jakarta.validation.constraints.NotNull

data class ClienteDTO (
    @field: NotNull(message = "El nombre no puede estar vacío")
    val nombreCompleto: String,

    @field: NotNull(message = "El número de contacto no puede estar vacío")
    val numeroContacto: String,

    @field: NotNull(message = "La dirección no puede estar vacía")
    val direccion: String,

    @field: NotNull(message = "La edad no puede estar vacía")
    val edad: Int,

    @field: NotNull(message = "El número de identificación no puede estar vacío")
    val numeroIdentificacion: String,

    @field:NotNull(message = "El ID del usuario es obligatorio")
    val usuarioId: Long

)