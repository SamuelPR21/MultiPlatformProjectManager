package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "maquina")
data class Maquina(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "modelo")
    var modelo: String?,

    @Column(name = "nombre")
    var nombre: String?,

    @Column(name = "ubicacion")
    var ubicacion: String?,

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    var estado: Estado,

    @ManyToOne
    @JoinColumn(name = "id_empleado_encargado")
    var empleadoEncargado: Empleado?,

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    var usuario: Usuarios

)

enum class Estado {
    LABORANDO,
    MANTENIMIENTO,
    BARADO
}

