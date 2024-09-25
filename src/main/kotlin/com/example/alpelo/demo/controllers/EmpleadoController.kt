package com.example.alpelo.demo.controllers

import com.example.alpelo.demo.models.Empleado
import com.example.alpelo.demo.models.Usuarios
import com.example.alpelo.demo.repositories.EmpleadoRepository
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

    //fun createUser()

    @GetMapping("/search/{id}")
    fun getEmployeById(@PathVariable("id") empleadoId:  Long): ResponseEntity<Empleado>{
        val employe = empleadoRepository.findById(empleadoId).orElse(null)
        return if(employe != null) ResponseEntity(employe, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/update/{id}")
    fun updareEmployeById(@PathVariable("id") empleadoId: Long, @RequestBody empleado: Empleado) : ResponseEntity<Empleado> {
        val existingEmploye = empleadoRepository.findById(empleadoId).orElse(null)

        if(existingEmploye == null){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val  updateEmploye = existingEmploye.copy(
            nombreCompleto = empleado.nombreCompleto,
            numeroContacto = empleado.numeroContacto,
            direccion = empleado.direccion,
            edad = empleado.edad,
            cargo = empleado.cargo
        )

        empleadoRepository.save(updateEmploye)
        return ResponseEntity(updateEmploye, HttpStatus.OK)

    }

    @DeleteMapping("/elminar/{id}")
    fun deleteUserById(@PathVariable("id") usuariosId: Long): ResponseEntity<Empleado>{
        if(!empleadoRepository.existsById(usuariosId)){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        empleadoRepository.deleteById(usuariosId)
        return  ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
