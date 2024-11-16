package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "trabajo_maquina")
data class TrabajoMaquina(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_trabajo", nullable = false)
    val trabajo: Trabajo,

    @ManyToOne
    @JoinColumn(name = "id_maquina", nullable = false)
    var maquina: Maquina
)
