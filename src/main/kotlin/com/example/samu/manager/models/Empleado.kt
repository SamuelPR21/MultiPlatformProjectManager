package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "empleado")
data class Empleado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre_completo", nullable = false)
    var nombreCompleto: String,

    @Column(name = "numero_contacto")
    var numeroContacto: String?,

    @Column(name = "direccion")
    var direccion: String?,

    @Column(name = "fecha_nacimiento")
    var fechaNacimiento: java.time.LocalDate?,

    @Column(name = "numero_identificacion", unique = true, nullable = false)
    val numeroIdentificacion: String,

    @Column(name = "cargo")
    var cargo: String?,

    @Column(name = "nivel", nullable = false)
    @Enumerated(EnumType.STRING)
    var nivel: Nivel
)

enum class Nivel {
    SUPERVISOR,
    PATO,
    ESTANDAR
}
