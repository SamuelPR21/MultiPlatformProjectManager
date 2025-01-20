package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "trabajo")
data class Trabajo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre", nullable = false)
    var nombre: String,

    @Column(name = "descripcion_corta")
    var descripcionCorta: String?,

    @Column(name = "estado")
    var estado: String?,

    @Column(name = "fecha_inicio")
    var fechaInicio: java.time.LocalDate?,

    @Column(name = "fecha_finalizacion")
    var fechaFinalizacion: java.time.LocalDate?,

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    var cliente: Cliente,

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    var usuario: Usuarios,

    @OneToMany(mappedBy = "trabajo", cascade = [CascadeType.ALL], orphanRemoval = true)
    val actividades: MutableList<Actividad> = mutableListOf()
)