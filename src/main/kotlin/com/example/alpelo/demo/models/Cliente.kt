package com.example.alpelo.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre_completo")
    val nombreCompleto: String,

    @Column(name = "numero_contacto")
    val numeroContacto: String,

    @Column(name = "direccion")
    val direccion: String,

    @Column(name = "edad")
    val edad: Int,

    @Column(name = "numero_identificacion", unique = true)
    val numeroIdentificacion: String
)
