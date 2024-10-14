package com.example.samu.manager.repositories

import com.example.samu.manager.models.Pago
import org.springframework.data.repository.CrudRepository

interface PagoRepository: CrudRepository<Pago, Long> {
}