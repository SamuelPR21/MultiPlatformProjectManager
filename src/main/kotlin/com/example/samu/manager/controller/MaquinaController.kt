package com.example.samu.manager.controller

import com.example.samu.manager.models.Estado
import com.example.samu.manager.models.Maquina
import com.example.samu.manager.repositories.EmpleadoRepository
import com.example.samu.manager.repositories.MaquinaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/maquina")
class MaquinaController(
    @Autowired private val maquinaRepository: MaquinaRepository,
    private val empleadoRepository: EmpleadoRepository
){

    @GetMapping("/todos")
    fun getAllMaquina(): List<Maquina> =
        maquinaRepository.findAll().toList()

    @PostMapping("/crear")
    fun createMaquina(@RequestBody maquina: Maquina): ResponseEntity<Maquina> {
        val newTipoTrabajo = maquinaRepository.save(maquina)
        return ResponseEntity.ok(newTipoTrabajo)
    }

    @GetMapping("/search/{id}")
    fun getMaquinaById(@PathVariable("id") MaquinaId:  Long): ResponseEntity<Maquina> {
        val employe = maquinaRepository.findById(MaquinaId).orElse(null)
        return if(employe != null) ResponseEntity(employe, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PatchMapping("/update/{id}")
    fun updateMaquinaById(@PathVariable id:Long, @RequestBody maquina: Map<String, Any>): ResponseEntity<Maquina?> {
        val  maquinaExistante = maquinaRepository.findById(id)
        if(maquinaExistante.isPresent){
            val maquinaActual = maquinaExistante.get()

            if (maquina.containsKey("modelo")) {
                maquinaActual.modelo = maquina["modelo"] as String
            }
            if (maquina.containsKey("nombre")) {
                maquinaActual.nombre = maquina["nombre"] as String
            }
            if (maquina.containsKey("ubicacion")) {
                maquinaActual.ubicacion = maquina["ubicacion"] as String
            }
            if (maquina.containsKey("ubicacion")) {
                maquinaActual.ubicacion = maquina["ubicacion"] as String
            }
            if (maquina.containsKey("estado")) {
                val estadoStrg = maquina["estado"] as String
                maquinaActual.estado = Estado.valueOf(estadoStrg)
            }
            if (maquina.containsKey("empleadoEncargado")) {
                val empleadoId = maquina["empleadoEncargado"] as Long
                val empleadoExistente = empleadoRepository.findById(empleadoId)

                if (empleadoExistente.isPresent){
                    maquinaActual.empleadoEncargado = empleadoExistente.get()
                }else{
                    println("Empleado con ID $empleadoId no encontrado. El campo no se actualizará.")
                }
            }

            return ResponseEntity.ok(maquinaRepository.save(maquinaActual))

        }
        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/elminar/{id}")
    fun deleteMaquinaById(@PathVariable("id") maquinaId: Long): ResponseEntity<Maquina> {
        if(!maquinaRepository.existsById(maquinaId)){
            return  ResponseEntity(HttpStatus.NOT_FOUND)
        }

        maquinaRepository.deleteById(maquinaId)
        return  ResponseEntity(HttpStatus.NO_CONTENT)
    }

}
