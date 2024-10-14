package com.example.samu.manager.models

import jakarta.persistence.*

@Entity
@Table(name = "pago")
data class Pago(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "id_trabajo", nullable = false)
    val trabajo: Trabajo,

    @Column(name = "monto", nullable = false)
    var monto: java.math.BigDecimal,

    @Column(name = "fecha_pago", nullable = false)
    var fechaPago: java.time.LocalDate,

    @Column(name = "medio_pago", nullable = false)
    @Enumerated(EnumType.STRING)
    var medioPago: MedioPago,

    @Column(name = "detalles_pago")
    var detallesPago: String?
)

enum class MedioPago {
    ELECTRÓNICO,
    EFECTIVO
}