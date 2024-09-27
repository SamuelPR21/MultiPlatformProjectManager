package com.example.alpelo.demo.repositories

import com.example.alpelo.demo.models.Cliente
import org.springframework.data.repository.CrudRepository

interface ClienteRepository: CrudRepository<Cliente, Long> {
}