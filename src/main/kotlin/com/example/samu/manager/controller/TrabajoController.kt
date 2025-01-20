package com.example.samu.manager.controller

import com.example.samu.manager.models.Trabajo
import com.example.samu.manager.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate


@RestController
@RequestMapping("/job")
class TrabajoController(
    @Autowired private val trabajoRepository: TrabajoRepository,
    private val tipoTrabajoRepository: TipoTrabajoRepository,
    private val usuarioReposittory: UsuarioReposittory,
    private val clienteRepository: ClienteRepository,
    private val empleadoRepository: EmpleadoRepository,
    private val maquinaRepository: MaquinaRepository,
    private val trabajoMaquinaRepository: TrabajoMaquinaRepository,
    private val trabajoEmpleadoRepository: TrabajoEmpleadoRepository
){

    @GetMapping("/todos")
    fun getAllTrabajo(): List<Trabajo> =
        trabajoRepository.findAll().toList()

    //Se debe modificar
    @PostMapping("/crear")
    fun createJob(@RequestBody trabajo: Trabajo): ResponseEntity<Trabajo> {
        val newJob = trabajoRepository.save(trabajo)
        return ResponseEntity.ok(newJob)
    }

    @GetMapping("/search/{id}")
    fun getJobById(@PathVariable("id") trabajoId:  Long): ResponseEntity<Trabajo> {
        val pago = trabajoRepository.findById(trabajoId).orElse(null)
        return if(pago != null) ResponseEntity(pago, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }
    //Se debe modificar
    @PatchMapping("/update/{id}")
    fun updateJobById(@PathVariable id:Long,
                      @RequestBody trabajo: Map<String, Any>):
            Any? {
        val  jobExistante = trabajoRepository.findById(id)

        if(!jobExistante.isPresent){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("El trabdo con ID  $id no existe en la base de datos.")

        }

        if(jobExistante.isPresent){
            val jobActual = jobExistante.get()

            if (trabajo.containsKey("nombre")) {
                jobActual.nombre = trabajo["nombre"] as String
            }
            if (trabajo.containsKey("descripcion_corta")) {
                jobActual.descripcionCorta = trabajo ["descripcion_corta"] as String
            }
            if (trabajo.containsKey("fecha_inicio")) {
                jobActual.fechaInicio = LocalDate.parse(trabajo["fecha_inicio"] as String)
            }
            if (trabajo.containsKey("estado")) {
                jobActual.estado = trabajo["estado"] as String
            }
            if (trabajo.containsKey("cliente")){
                val clienteId = trabajo["cliente"] as Long
                val clienteExistente = clienteRepository.findById(clienteId)

                if (clienteExistente.isPresent){
                    jobActual.cliente = clienteExistente.get()
                }else{
                    println("Cliente con ID $clienteId no encontrado. El campo no se actualizará.")
                }
            }

            if (trabajo.containsKey("usuario")) {
                val usuarioId = trabajo["usuario"] as Long
                val usuarioExistente = usuarioReposittory.findById(usuarioId)

                if (usuarioExistente.isPresent){
                    jobActual.usuario = usuarioExistente.get()
                }else{
                    println("Usuario con ID $usuarioId no encontrado. El campo no se actualizará.")
                }

            }


            val empleadosActualizdos = mutableListOf<Long>()
            if (trabajo.containsKey("empleado")){
                for(empleadoUpdate in trabajo["empleado"] as List<Map<String, Long>>){
                    val antiguioId = empleadoUpdate["id"] ?: return ResponseEntity.badRequest()
                        .body("Falta el campo 'id' en empleado")
                    val nuevoId = empleadoUpdate["idNuevo"] ?: return ResponseEntity.badRequest()
                        .body("Falta el campo 'idNuevo' en empleado")

                    val trabajoEmpleadoOptional = trabajoEmpleadoRepository.findAll().find {
                      it.trabajo.id == jobActual.id && it.empleado.id == antiguioId
                    }

                    if(trabajoEmpleadoOptional == null){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No se encontró el empleado con ID $antiguioId en el trabajo con ID ${jobActual.id}")
                    }

                    val nuevoEmpleadoOptional = empleadoRepository.findById(nuevoId)
                    if (!nuevoEmpleadoOptional.isPresent){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No se encontró el empleado con ID $nuevoId no existe")
                    }

                    val trabajoEmpleado = trabajoEmpleadoOptional
                    trabajoEmpleado.empleado = nuevoEmpleadoOptional.get()
                    trabajoEmpleadoRepository.save(trabajoEmpleado)
                    empleadosActualizdos.add(nuevoId)

                }
            }



            val maquinasActualizadas = mutableListOf<Long>()
            if (trabajo.containsKey("maquina")) {
                for (maquinaUpdate in trabajo["maquina"] as List<Map<String, Long>>) {
                    val antiguoId = maquinaUpdate["id"] ?: return ResponseEntity.badRequest()
                        .body("Falta el campo 'id' en maquina")
                    val nuevoId = maquinaUpdate["idNuevo"] ?: return ResponseEntity.badRequest()
                        .body("Falta el campo 'idNuevo' en maquina")

                    val trabajoMaquinaOptional = trabajoMaquinaRepository.findAll().find {
                        it.trabajo.id == jobActual.id && it.maquina.id == antiguoId
                    }

                    if (trabajoMaquinaOptional == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No se encontró la máquina con ID $antiguoId en el trabajo con ID ${jobActual.id}")
                    }

                    val nuevaMaquinaOptional = maquinaRepository.findById(nuevoId)
                    if (!nuevaMaquinaOptional.isPresent) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("No se encontró la máquina con ID $nuevoId")
                    }

                    val trabajoMaquina = trabajoMaquinaOptional
                    trabajoMaquina.maquina = nuevaMaquinaOptional.get()
                    trabajoMaquinaRepository.save(trabajoMaquina)
                    maquinasActualizadas.add(nuevoId)

                }
            }


            return trabajoRepository.save(jobActual)
        }

        return null
    }

    @DeleteMapping("/eliminar/{id}")
    fun deleteJobById(@PathVariable("id") trabajoId: Long): ResponseEntity<Trabajo> {
        if (!trabajoRepository.existsById(trabajoId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        trabajoRepository.deleteById(trabajoId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}