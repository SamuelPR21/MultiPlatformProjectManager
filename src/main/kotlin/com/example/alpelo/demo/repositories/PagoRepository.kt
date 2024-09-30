package com.example.alpelo.demo.repositories

import com.example.alpelo.demo.models.Pago
import org.springframework.data.repository.CrudRepository

interface PagoRepository: CrudRepository<Pago, Long> {
}