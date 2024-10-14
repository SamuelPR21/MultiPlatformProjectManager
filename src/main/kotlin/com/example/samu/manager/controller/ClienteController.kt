package com.example.samu.manager.controller

import com.example.samu.manager.models.Cliente
import com.example.samu.manager.repositories.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/cliente")

class ClienteController(@Autowired private val clienteRepository: ClienteRepository) {

    @GetMapping("/todos")
    fun getAllCustemer():List<Cliente> =
        clienteRepository.findAll().toList()

    @PostMapping("/crear")
    fun createCustomer(@RequestBody cliente: Cliente): ResponseEntity<Cliente> {
        val newEmploye = clienteRepository.save(cliente)
        return ResponseEntity.ok(newEmploye)
    }
    @GetMapping("/search/{id}")
    fun getCustomerById(@PathVariable("id") clienteId:  Long): ResponseEntity<Cliente> {
        val employe = clienteRepository.findById(clienteId).orElse(null)
        return if(employe != null) ResponseEntity(employe, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PatchMapping("/update/{id}")
    fun updateCustomerById(@PathVariable id:Long, @RequestBody cliente: Map<String, Any>): Cliente? {
        val  customerExistante = clienteRepository.findById(id)
        if(customerExistante.isPresent){
            val customerActual = customerExistante.get()

            if (cliente.containsKey("nombreCompleto")) {
                customerActual.nombreCompleto= cliente["nombreCompleto"] as String
            }
            if (cliente.containsKey("numeroContacto")) {
                customerActual.numeroContacto = cliente["numeroContacto"] as String
            }
            if (cliente.containsKey("direccion")) {
                customerActual.direccion = cliente["direccion"] as String
            }
            if (cliente.containsKey("edad")) {
                customerActual.edad = cliente["edad"] as Int
            }

            return clienteRepository.save(customerActual)

        }
        return null
    }

    @DeleteMapping("/elminar/{id}")
    fun deleteCustomerById(@PathVariable("id") CustomerId: Long): ResponseEntity<Cliente> {
        if(!clienteRepository.existsById(CustomerId)){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        clienteRepository.deleteById(CustomerId)
        return  ResponseEntity(HttpStatus.NO_CONTENT)
    }

}