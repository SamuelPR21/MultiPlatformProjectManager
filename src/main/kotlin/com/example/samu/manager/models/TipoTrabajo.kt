package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "tipo_trabajo")
data class TipoTrabajo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre", nullable = false)
    var nombre: String,

    @Column(name = "descripcion")
    var descripcion: String?,

    @Column(name = "valorUnidad")
    var valorUnidad: Int
)
