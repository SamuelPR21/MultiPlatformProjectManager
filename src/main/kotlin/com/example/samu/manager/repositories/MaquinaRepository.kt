package com.example.samu.manager.repositories

import com.example.samu.manager.models.Maquina
import org.springframework.data.repository.CrudRepository

interface MaquinaRepository: CrudRepository<Maquina, Long> {
}