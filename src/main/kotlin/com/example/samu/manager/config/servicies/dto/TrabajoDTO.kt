package com.example.samu.manager.config.servicies.dto

import jakarta.validation.constraints.NotNull

data class TrabajoDTO (
    @field: NotNull(message = "El nombre no puede estar vacío")
    val nombre: String,

    @field: NotNull(message = "La descripcion no puede estar vacia")
    val descripcionCorta: String,

    @field: NotNull(message = "el estado no puede estar vacio")
    val estado:String,

    @field: NotNull(message = "la fecha no puede star vacia")
    val fechaInicio: java.time.LocalDate,

    @field: NotNull(message = "la fecha no puede star vacia")
    val fechaFinalizacion: java.time.LocalDate,

    @field: NotNull(message = "el id de cliente no puede estar vacia ")
    val cliente: Long,

    @field: NotNull(message = "el id de suario no puede estar vacio")
    val usuario: Long,

    @field: NotNull(message = "La lista de IDs de empleados no puede estar vacía")
    val empleados: List<Long>,

    @field: NotNull(message = "La lista de IDs de máquinas no puede estar vacía")
    val maquinas: List<Long>

)