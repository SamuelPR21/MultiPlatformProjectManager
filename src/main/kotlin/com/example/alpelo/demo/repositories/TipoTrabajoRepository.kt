package com.example.alpelo.demo.repositories

import com.example.alpelo.demo.models.TipoTrabajo
import org.springframework.data.repository.CrudRepository

interface TipoTrabajoRepository: CrudRepository<TipoTrabajo, Long> {

}