package com.example.samu.manager.controller

import com.example.samu.manager.config.servicies.dto.ActividadDTO
import com.example.samu.manager.models.Actividad
import com.example.samu.manager.repositories.ActividadRepository
import com.example.samu.manager.repositories.TipoTrabajoRepository
import com.example.samu.manager.repositories.TrabajoRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/actvidad")
class ActidadController(
    private val trabajoRepository: TrabajoRepository,
    private val actividadRepository: ActividadRepository,
    private val tipoTrabajoRepository: TipoTrabajoRepository
) {

    //Registrar actividad
    @PostMapping("/{idTrabajo}/crear")
    fun registrarActividad(@RequestBody @Valid actividadDTO: ActividadDTO): ResponseEntity<Actividad>{
        val tipoTrabajo = tipoTrabajoRepository.findById(actividadDTO.tipoTrabajoId)

            .orElseThrow { IllegalArgumentException("El tipo de trabajo con ID ${actividadDTO.tipoTrabajoId} no existe") }

        val trabajo = trabajoRepository.findById(actividadDTO.trabajoId)
            .orElseThrow { IllegalArgumentException("El trabajo con ID ${actividadDTO.trabajoId} no existe") }

        val actividad = Actividad(
            id = 0L,
            fechaTrabajada = actividadDTO.fechaTrabajada,
            cantidadUnidades = actividadDTO.cantidadUnidades,
            tipoTrabajo = tipoTrabajo,
            trabajo = trabajo
        )

        val nuevaActividad = actividadRepository.save(actividad)
        return  ResponseEntity.ok(nuevaActividad)
    }


    //Consultar Actividades por IdTrabajo
    @GetMapping("/{idTrabajo}/actividad")
    fun obtenerActividadesPorTrabajo(@PathVariable idTrabajo: Long): ResponseEntity<List<Actividad>> {
        val actividades = actividadRepository.findByTrabajoId(idTrabajo)
        return ResponseEntity.ok(actividades)
    }

    //Eliminar actividad de un trabajo
    @DeleteMapping("/eliminar/{idActividad}")
    fun eliminarActividad(@PathVariable idActividad: Long): ResponseEntity<Any> {
        if (actividadRepository.existsById(idActividad)) {
            actividadRepository.deleteById(idActividad)
            return ResponseEntity.ok("Actividad eliminada correctamente.")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actividad con ID $idActividad no encontrada.")
    }

    //Actualizar actividad
    @PatchMapping("/actualizar/{idActividad}")
    fun actualizarActividad( @PathVariable idActividad: Long,
                             @RequestBody nuevaActividad: Map<String, Any>
    ): ResponseEntity<Any> {
        val actividadExistente = actividadRepository.findById(idActividad)

        if (actividadExistente.isPresent) {

            val actividadActual = actividadExistente.get()

            if(nuevaActividad.containsKey("fecha_trabajada")){
                actividadActual.fechaTrabajada = LocalDate.parse(nuevaActividad["fecha_trabajada"].toString())
            }

            if (nuevaActividad.containsKey("cantidad_unidades")) {
                actividadActual.cantidadUnidades = nuevaActividad["cantidad_unidades"].toString().toInt()
            }

            actividadRepository.save(actividadActual)
            return ResponseEntity.ok("Actividad actualizada correctamente.")

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actividad con ID $idActividad no encontrada.")    }


    //Obtener actividades por rango de fechas
    @GetMapping("/rango-fechas")
    fun obtenerActividadesPorRangoDeFechas(@RequestParam fechaInicio: String, @RequestParam fechaFin: String
    ): ResponseEntity<List<Actividad>> {
        val inicio = LocalDate.parse(fechaInicio)
        val fin = LocalDate.parse(fechaFin)
        val actividades = actividadRepository.findAllByFechaTrabajadaBetween(inicio, fin)
        return ResponseEntity.ok(actividades)
    }

    //Calcular total trabajo
    @GetMapping("/{idTrabajo}/total")
    fun calcularTotalPorTrabajo(@PathVariable idTrabajo: Long): ResponseEntity<Map<String, Any>> {
        val actividades = actividadRepository.findByTrabajoId(idTrabajo)
        if (actividades.isNotEmpty()) {
            val total = actividades.sumOf { it.cantidadUnidades + it.tipoTrabajo.valorUnidad }
            return ResponseEntity.ok(mapOf("total" to total))
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            mapOf("mensaje" to "No se encontraron actividades para el trabajo con ID $idTrabajo.")
        )
    }

    //Consultar Actividades por Tipo de Trabajo
    @GetMapping("/{idTrabajo}/tipo-trabajo/{idTipoTrabajo}")
    fun obtenerActividadessPorTipoTrabajo(@PathVariable idTrabajo: Long, @PathVariable idTipoTrabajo: Long): ResponseEntity<List<Actividad>>{
        val actividades = actividadRepository.findByTrabajoIdAndTipoTrabajoId(idTrabajo, idTipoTrabajo)
        return ResponseEntity.ok(actividades)
    }

    //Resumen de Trabajo
    @GetMapping("/{idTrabajo}/resumen")
    fun obtenerResumenPorTrabajo(@PathVariable idTrabajo: Long): ResponseEntity<Any>{
        val actividades = actividadRepository.findByTrabajoId(idTrabajo)
        if (actividades.isNotEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron actividades para el trabajo con ID $idTrabajo.")
        }

        //Agrupar por tipo de trabajo
        val resumen = actividades.groupBy { it.tipoTrabajo}.map { (tipoTrabajo, listaActividades) ->
            mapOf(
                "tipo_trabajo" to tipoTrabajo.nombre,
                "totalUnidades" to listaActividades.sumOf { it.cantidadUnidades },
                "valorTotal" to listaActividades.sumOf { it.cantidadUnidades * tipoTrabajo.valorUnidad }
            )

        }

        //Calcular totales generales
        val totalUnidades = actividades.sumOf { it.cantidadUnidades }
        val valorTotal = actividades.sumOf { it.cantidadUnidades * it.tipoTrabajo.valorUnidad }

        val resultado = mapOf(
            "resumenPorTipoTrabajo" to resumen,
            "totales" to mapOf(
                "totalUnidades" to totalUnidades,
                "valorTotal" to valorTotal
            )
        )

        return ResponseEntity.ok(resultado)
    }


}
