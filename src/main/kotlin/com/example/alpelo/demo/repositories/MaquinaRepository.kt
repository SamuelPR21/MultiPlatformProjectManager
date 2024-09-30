package com.example.alpelo.demo.repositories

import com.example.alpelo.demo.models.Maquina
import org.springframework.data.repository.CrudRepository

interface MaquinaRepository: CrudRepository<Maquina, Long> {
}