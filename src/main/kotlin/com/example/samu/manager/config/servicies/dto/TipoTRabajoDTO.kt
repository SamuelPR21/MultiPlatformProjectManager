package com.example.samu.manager.config.servicies.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class TipoTRabajoDTO (
    @field:NotNull(message = "El nombre del tipo de trabajo no puede estar vacío")
    val nombre: String,

    @field:NotNull(message = "La descripción del tipo de trabajo no puede estar vacía")
    val descripcion: String,

    @field:Positive(message = "El valor de la unidad no puede estar vacío")
    val valorUnidad: Int
)