package com.example.samu.manager.repositories

import com.example.samu.manager.models.TipoTrabajo
import org.springframework.data.repository.CrudRepository

interface TipoTrabajoRepository: CrudRepository<TipoTrabajo, Long> {

}