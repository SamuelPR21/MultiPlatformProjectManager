package com.example.samu.manager.models

import com.example.samu.manager.models.TrabajoEmpleado
import com.example.samu.manager.models.TrabajoMaquina
import com.fasterxml.jackson.annotation.JsonIgnore

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

    @JsonIgnore
    @OneToMany(mappedBy = "trabajo")
    var empleados: MutableList<TrabajoEmpleado> = mutableListOf(),

    @JsonIgnore
    @OneToMany(mappedBy = "trabajo")
    var maquinas: MutableList<TrabajoMaquina> = mutableListOf()

)