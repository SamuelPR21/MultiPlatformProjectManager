package com.example.alpelo.demo.repositories

import com.example.alpelo.demo.models.Empleado
import org.springframework.data.repository.CrudRepository

interface EmpleadoRepository: CrudRepository<Empleado,Long> {
}