package com.example.samu.manager.repositories

import com.example.samu.manager.models.TrabajoEmpleado
import org.springframework.data.repository.CrudRepository

interface TrabajoEmpleadoRepository: CrudRepository<TrabajoEmpleado, Long> {
}