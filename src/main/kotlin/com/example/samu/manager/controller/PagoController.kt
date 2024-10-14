package com.example.samu.manager.controller

import com.example.samu.manager.models.MedioPago
import com.example.samu.manager.models.Pago
import com.example.samu.manager.repositories.PagoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pago")
class PagoController(@Autowired private val pagoRepository: PagoRepository){
    @GetMapping("/todos")
    fun getAllPago(): List<Pago> =
        pagoRepository.findAll().toList()

    @PostMapping("/crear")
    fun createPago(@RequestBody pago: Pago): ResponseEntity<Pago> {
        val newPago = pagoRepository.save(pago)
        return ResponseEntity.ok(newPago)
    }

    @GetMapping("/search/{id}")
    fun getEmployeById(@PathVariable("id") pagoId:  Long): ResponseEntity<Pago> {
        val pago = pagoRepository.findById(pagoId).orElse(null)
        return if(pago != null) ResponseEntity(pago, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PatchMapping("/update/{id}")
    fun updareEmployeById(@PathVariable id:Long, @RequestBody pago: Map<String, Any>): Pago? {
        val  pagoExistante = pagoRepository.findById(id)
        if(pagoExistante.isPresent){
            val pagoActual = pagoExistante.get()

            if (pago.containsKey("monto")) {
                pagoActual.monto = java.math.BigDecimal(pago["monto"] as Double)
            }
            if (pago.containsKey("fecha_pago")) {
                pagoActual.fechaPago = java.time.LocalDate.parse(pago["fecha_pago"] as String)
            }
            if (pago.containsKey("detallesPago")) {
                pagoActual.detallesPago = pago["detallesPago"] as String
            }
            if (pago.containsKey("medio_pago")) {
                val medioPagoStr = pago["medio_pago"] as String
                pagoActual.medioPago = MedioPago.valueOf(medioPagoStr)
            }
            return pagoRepository.save(pagoActual)

        }
        return null
    }

    @DeleteMapping("/eliminar/{id}")
    fun deletePagoById(@PathVariable("id") pagoId: Long): ResponseEntity<Pago> {
        if (!pagoRepository.existsById(pagoId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        pagoRepository.deleteById(pagoId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}