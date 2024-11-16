package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "trabajo_empleado")
data class TrabajoEmpleado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "id_trabajo", nullable = false)
    val trabajo: Trabajo,

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    var empleado: Empleado
)