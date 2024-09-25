package com.example.alpelo.demo.models

import jakarta.persistence.*

@Entity
@Table(name = "empleado")
data class Empleado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre_completo", nullable = false)
    val nombreCompleto: String,

    @Column(name = "numero_contacto")
    val numeroContacto: String?,

    @Column(name = "direccion")
    val direccion: String?,

    @Column(name = "edad")
    val edad: Int?,

    @Column(name = "numero_identificacion", unique = true)
    val numeroIdentificacion: String?,

    @Column(name = "cargo")
    val cargo: String?,

    @Column(name = "nivel", nullable = false)
    @Enumerated(EnumType.STRING)
    val nivel: Nivel
)

enum class Nivel {
    SUPERVISOR,
    PATO
}
