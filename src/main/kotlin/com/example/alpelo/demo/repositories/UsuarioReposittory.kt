package com.example.alpelo.demo.repositories


import com.example.alpelo.demo.models.Usuarios
import org.springframework.data.repository.CrudRepository


interface  UsuarioReposittory: CrudRepository<Usuarios,Long> {

    }
