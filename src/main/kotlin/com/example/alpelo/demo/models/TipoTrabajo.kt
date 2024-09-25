package com.example.alpelo.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "tipo_trabajo")
data class TipoTrabajo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre", nullable = false)
    val nombre: String,

    @Column(name = "descripcion")
    val descripcion: String?
)
