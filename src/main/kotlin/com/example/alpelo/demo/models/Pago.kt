package com.example.alpelo.demo.models

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
    val monto: java.math.BigDecimal,

    @Column(name = "fecha_pago", nullable = false)
    val fechaPago: java.time.LocalDate,

    @Column(name = "medio_pago", nullable = false)
    @Enumerated(EnumType.STRING)
    val medioPago: MedioPago,

    @Column(name = "detalles_pago")
    val detallesPago: String?
)

enum class MedioPago {
    ELECTRÓNICO,
    EFECTIVO
}
