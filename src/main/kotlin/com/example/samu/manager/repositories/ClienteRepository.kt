package com.example.samu.manager.repositories

import com.example.samu.manager.models.Cliente
import org.springframework.data.repository.CrudRepository

interface ClienteRepository: CrudRepository<Cliente, Long> {
}