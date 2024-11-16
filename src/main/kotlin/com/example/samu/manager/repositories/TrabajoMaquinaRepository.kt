package com.example.samu.manager.repositories

import com.example.samu.manager.models.TrabajoMaquina
import org.springframework.data.repository.CrudRepository

interface TrabajoMaquinaRepository: CrudRepository<TrabajoMaquina, Long> {
}