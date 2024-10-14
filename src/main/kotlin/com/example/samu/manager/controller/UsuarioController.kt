package com.example.samu.manager.controller

import com.example.samu.manager.models.Usuarios
import com.example.samu.manager.repositories.UsuarioReposittory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UsuarioController (@Autowired private  val usuarioReposittory: UsuarioReposittory){

    @GetMapping("/todos")
    fun getAllUsers(): List<Usuarios> =
        usuarioReposittory.findAll().toList()

    @PostMapping("/crear")
    fun createUser(@RequestBody usuario: Usuarios): ResponseEntity<Usuarios> {
        val nuevoUsuario = usuarioReposittory.save(usuario)
        return ResponseEntity.ok(nuevoUsuario)
    }

    @GetMapping("/search/{id}")
    fun getUserById(@PathVariable("id") usuariosId: Long): ResponseEntity<Usuarios> {
        val user= usuarioReposittory.findById(usuariosId).orElse(null)
        return if(user != null) ResponseEntity(user, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)

    }

    @PutMapping("/update/{id}")
    fun updateUserById(@PathVariable("id") usuariosId: Long, @RequestBody usuarios: Usuarios) : ResponseEntity<Usuarios> {
        val existingUser = usuarioReposittory.findById(usuariosId). orElse(null)

        if (existingUser ==null){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val  updateUser = existingUser.copy(
            nombreUsuario = usuarios.nombreUsuario,
            nombreCompleto = usuarios.nombreCompleto,
            contrasena = usuarios.contrasena,
            correoElectronico = usuarios.correoElectronico
        )

        usuarioReposittory.save(updateUser)
        return ResponseEntity(updateUser, HttpStatus.OK)
    }

    @DeleteMapping("/elminar/{id}")
    fun deleteUserById(@PathVariable("id") usuariosId: Long): ResponseEntity<Usuarios> {
        if(!usuarioReposittory.existsById(usuariosId)){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        usuarioReposittory.deleteById(usuariosId)
        return  ResponseEntity(HttpStatus.NO_CONTENT)
    }
}