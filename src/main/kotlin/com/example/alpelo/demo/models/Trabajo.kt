package com.example.alpelo.demo.models

import com.example.alpelo.demo.models.Cliente
import com.example.alpelo.demo.models.Usuarios
import jakarta.persistence.*

@Entity
@Table(name = "trabajo")
data class Trabajo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "nombre", nullable = false)
    val nombre: String,

    @Column(name = "descripcion_corta")
    val descripcionCorta: String?,

    @Column(name = "estado")
    val estado: String?,

    @Column(name = "fecha_inicio")
    val fechaInicio: java.time.LocalDate?,

    @Column(name = "fecha_finalizacion")
    val fechaFinalizacion: java.time.LocalDate?,

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    val cliente: Cliente,

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    val usuario: Usuarios,

    @ManyToOne
    @JoinColumn(name = "id_tipo_trabajo")
    val tipoTrabajo: TipoTrabajo
)
