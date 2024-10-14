package com.example.samu.manager.controller

import com.example.samu.manager.models.Empleado
import com.example.samu.manager.models.Nivel
import com.example.samu.manager.repositories.EmpleadoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/empleado")

class EmpleadoControllerController (@Autowired private  val empleadoRepository: EmpleadoRepository){

    @GetMapping("/todos")
    fun getAllEmploye(): List<Empleado> =
        empleadoRepository.findAll().toList()

    @PostMapping("/crear")
    fun createEmploye(@RequestBody empleado: Empleado): ResponseEntity<Empleado> {
        val newEmploye = empleadoRepository.save(empleado)
        return ResponseEntity.ok(newEmploye)
    }

    @GetMapping("/search/{id}")
    fun getEmployeById(@PathVariable("id") empleadoId:  Long): ResponseEntity<Empleado> {
        val employe = empleadoRepository.findById(empleadoId).orElse(null)
        return if(employe != null) ResponseEntity(employe, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PatchMapping("/update/{id}")
    fun updareEmployeById(@PathVariable id:Long, @RequestBody empleado: Map<String, Any>): Empleado? {
        val  empleadoExistante = empleadoRepository.findById(id)
        if(empleadoExistante.isPresent){
            val empleadoActual = empleadoExistante.get()

            if (empleado.containsKey("nombreCompleto")) {
                empleadoActual.nombreCompleto = empleado["nombreCompleto"] as String
            }
            if (empleado.containsKey("numeroContacto")) {
                empleadoActual.numeroContacto = empleado["numeroContacto"] as String
            }
            if (empleado.containsKey("direccion")) {
                empleadoActual.direccion = empleado["direccion"] as String
            }
            if (empleado.containsKey("cargo")) {
                empleadoActual.cargo = empleado["cargo"] as String
            }
            if (empleado.containsKey("nivel")) {
                val nivelStr = empleado["nivel"] as String
                empleadoActual.nivel = Nivel.valueOf(nivelStr)
            }

            return empleadoRepository.save(empleadoActual)

        }
        return null
    }

    @DeleteMapping("/elminar/{id}")
    fun deleteUserById(@PathVariable("id") usuariosId: Long): ResponseEntity<Empleado> {
        if(!empleadoRepository.existsById(usuariosId)){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        empleadoRepository.deleteById(usuariosId)
        return  ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
