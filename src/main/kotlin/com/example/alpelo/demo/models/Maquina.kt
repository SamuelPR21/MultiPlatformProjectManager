package com.example.alpelo.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "maquina")
data class Maquina(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "modelo")
    val modelo: String?,

    @Column(name = "nombre")
    val nombre: String?,

    @Column(name = "ubicacion")
    val ubicacion: String?,

    @Column(name = "estado")
    val estado: String?,

    @ManyToOne
    @JoinColumn(name = "id_empleado_encargado")
    val empleadoEncargado: Empleado?
)
