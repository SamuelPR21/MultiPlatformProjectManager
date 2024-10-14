package com.example.samu.manager.controller

import com.example.samu.manager.models.TipoTrabajo
import com.example.samu.manager.repositories.TipoTrabajoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/TTrabajo")

class TipoTrabajoController(@Autowired private val tipoTrabajoRepository: TipoTrabajoRepository){
    @GetMapping("/todos")
    fun getAllTTrabajo(): List<TipoTrabajo> =
        tipoTrabajoRepository.findAll().toList()

    @PostMapping("/crear")
    fun createEmploye(@RequestBody tipoTrabajo: TipoTrabajo): ResponseEntity<TipoTrabajo> {
        val newTipoTrabajo = tipoTrabajoRepository.save(tipoTrabajo)
        return ResponseEntity.ok(newTipoTrabajo)
    }

    @GetMapping("/search/{id}")
    fun getEmployeById(@PathVariable("id") TTrabajoId:  Long): ResponseEntity<TipoTrabajo> {
        val employe = tipoTrabajoRepository.findById(TTrabajoId).orElse(null)
        return if(employe != null) ResponseEntity(employe, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PatchMapping("/update/{id}")
    fun updareEmployeById(@PathVariable id:Long, @RequestBody tipoTrabajo: Map<String, Any>): TipoTrabajo? {
        val  ttrabajoExistante = tipoTrabajoRepository.findById(id)
        if(ttrabajoExistante.isPresent){
            val ttrabajoActual = ttrabajoExistante.get()

            if (tipoTrabajo.containsKey("nombre")) {
                ttrabajoActual.nombre = tipoTrabajo["nombreCompleto"] as String
            }
            if (tipoTrabajo.containsKey("descripcion")) {
                ttrabajoActual.descripcion = tipoTrabajo["descripcion"] as String
            }
            if (tipoTrabajo.containsKey("valorUnidad")) {
                ttrabajoActual.valorUnidad = tipoTrabajo["valorUnidad"] as Int
            }

            return tipoTrabajoRepository.save(ttrabajoActual)

        }
        return null
    }

    @DeleteMapping("/elminar/{id}")
    fun deleteUserById(@PathVariable("id") ttrabajoId: Long): ResponseEntity<TipoTrabajo> {
        if(!tipoTrabajoRepository.existsById(ttrabajoId)){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        tipoTrabajoRepository.deleteById(ttrabajoId)
        return  ResponseEntity(HttpStatus.NO_CONTENT)
    }
}