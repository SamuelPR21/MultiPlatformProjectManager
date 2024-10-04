package com.example.alpelo.demo.repositories

import com.example.alpelo.demo.models.Trabajo
import org.springframework.data.repository.CrudRepository

interface TrabajoRepository: CrudRepository<Trabajo, Long> {
}