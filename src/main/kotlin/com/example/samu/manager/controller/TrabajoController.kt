package com.example.samu.manager.controller

import com.example.samu.manager.models.Trabajo
import com.example.samu.manager.repositories.TipoTrabajoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/job")
class TrabajoController(@Autowired private  val trabajoRepository: TipoTrabajoRepository,
                        private val tipoTrabajoRepository: TipoTrabajoRepository){
//    @GetMapping("/todos")
//    fun getAllTrabajo(): List<Trabajo> =
//        trabajoRepository.findAll().toList()
//
//    @PostMapping("/crear")
//    fun createJob(@RequestBody trabajo: Trabajo): ResponseEntity<Trabajo> {
//        val newJob = trabajoRepository.save(trabajo)
//        return ResponseEntity.ok(newJob)
//    }
//
//    @GetMapping("/search/{id}")
//    fun getJobById(@PathVariable("id") trabajoId:  Long): ResponseEntity<Trabajo> {
//        val pago = trabajoRepository.findById(trabajoId).orElse(null)
//        return if(pago != null) ResponseEntity(pago, HttpStatus.OK)
//        else ResponseEntity(HttpStatus.NOT_FOUND)
//    }
//
//    @PatchMapping("/update/{id}")
//    fun updateJobById(@PathVariable id:Long, @RequestBody trabajo: Map<String, Any>): Trabajo? {
//        val  jobExistante = trabajoRepository.findById(id)
//        if(jobExistante.isPresent){
//            val jobActual = jobExistante.get()
//
//            if (trabajo.containsKey("nombre")) {
//                jobActual.nombre = trabajo["nombre"] as String
//            }
//            if (trabajo.containsKey("descripcion_corta")) {
//                jobActual.descripcionCorta = trabajo ["descripcion_corta"] as String
//            }
//            if (trabajo.containsKey("estado")) {
//                jobActual.estado = trabajo["estado"] as String
//            }
//            if (trabajo.containsKey("tipoTrabajo")) {
//                val tipoTrabajoId = trabajo["tipoTrabajo"] as Long
//                val tipoTrabajoExistente = tipoTrabajoRepository.findById(tipoTrabajoId)
//
//                if (tipoTrabajoExistente.isPresent){
//                    jobActual.tipoTrabajo = tipoTrabajoExistente.get()
//                }else{
//                    println("el tipo de trabajo con ID $tipoTrabajoId no encontrado. El campo no se actualizará.")
//                }
//            }
//
//            return trabajoRepository.save(jobActual)
//
//        }
//        return null
//    }

    @DeleteMapping("/eliminar/{id}")
    fun deleteJobById(@PathVariable("id") trabajoId: Long): ResponseEntity<Trabajo> {
        if (!trabajoRepository.existsById(trabajoId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        trabajoRepository.deleteById(trabajoId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}