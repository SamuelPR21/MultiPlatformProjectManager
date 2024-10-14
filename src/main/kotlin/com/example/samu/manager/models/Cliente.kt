package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "cliente")
data class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre_completo")
    var nombreCompleto: String,

    @Column(name = "numero_contacto")
    var numeroContacto: String,

    @Column(name = "direccion")
    var direccion: String,

    @Column(name = "edad")
    var edad: Int,

    @Column(name = "numero_identificacion", unique = true)
    val numeroIdentificacion: String
)
