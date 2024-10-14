package com.example.samu.manager.repositories


import com.example.samu.manager.models.Empleado
import org.springframework.data.repository.CrudRepository

interface EmpleadoRepository: CrudRepository<Empleado,Long> {
}