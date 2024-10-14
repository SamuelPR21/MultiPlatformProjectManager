package com.example.samu.manager.models

import jakarta.persistence.*
@Entity
@Table(name = "usuario")
data class Usuarios (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "nombre_completo", nullable = false)
    var nombreCompleto: String,

    @Column(name = "correo_electronico", nullable = false, unique = true)
    var correoElectronico: String,

    @Column(name = "numero_identificacion", nullable = true)
    var numeroIdentificacion: String?,

    @Column(name = "fecha_nacimiento")
    var fechaNacimiento: java.time.LocalDate?,

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    var nombreUsuario: String,

    @Column(name = "contrasena", nullable = false)
    var contrasena: String,

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    val rol: Rol? = null
)

enum class Rol {
    SUPERUSUARIO,
    USUARIO_NORMAL
}
