package com.example.samu.manager.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "actividad")
data class Actividad(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "fecha_trabajada", nullable = false)
    var fechaTrabajada: LocalDate,

    @Column(name = "cantidad_unidades", nullable = false)
    var cantidadUnidades: Int,

    @ManyToOne
    @JoinColumn(name = "id_tipo_trabajo", nullable = false)
    val tipoTrabajo: TipoTrabajo,

    @ManyToOne
    @JoinColumn(name = "id_trabajo", nullable = false)
    var trabajo: Trabajo,


)
