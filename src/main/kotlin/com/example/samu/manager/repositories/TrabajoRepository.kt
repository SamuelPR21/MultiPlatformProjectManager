package com.example.samu.manager.repositories

import com.example.samu.manager.models.Trabajo
import org.springframework.data.repository.CrudRepository

interface TrabajoRepository: CrudRepository<Trabajo, Long> {
}