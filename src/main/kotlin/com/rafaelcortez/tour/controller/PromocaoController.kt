package com.rafaelcortez.tour.controller

import com.rafaelcortez.tour.model.Promocao
import com.rafaelcortez.tour.model.RespostaJson
import com.rafaelcortez.tour.service.PromocaoService
import com.rafaelcortez.tour.service.impl.PromocaoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocao"])
class PromocaoController {

    @Autowired
    lateinit var service: PromocaoService

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Promocao?>{
        var promocao = service.getbyId(id)
        var status = if(promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocao, status)
    }


    @GetMapping()
    fun getByLocal(@RequestParam(required = false, defaultValue = "") local: String) : ResponseEntity<List<Promocao>?> {
        var listaPromocoes = service.getByLocal(local)
        var status = if(listaPromocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(listaPromocoes, status)
    }

    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJson> {
        service.create(promocao)
        val respostaJson = RespostaJson("OK", Date())
        return ResponseEntity(respostaJson,HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete (@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(service.getbyId(id) != null){
            service.delete(id)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) : ResponseEntity<Unit>{
        var status = HttpStatus.NOT_FOUND
        if(service.getbyId(id) != null){
            service.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }


}