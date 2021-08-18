package com.rafaelcortez.tour.controller

import com.rafaelcortez.tour.model.Promocao
import com.rafaelcortez.tour.service.PromocaoService
import com.rafaelcortez.tour.service.impl.PromocaoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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
    fun getByLocal(@RequestParam(required = false, defaultValue = "") local: String) =
        service.getByLocal(local)

    @PostMapping()
    fun create(@RequestBody promocao: Promocao): ResponseEntity<Unit> {
        service.create(promocao)
        return ResponseEntity(Unit,HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete (@PathVariable id: Long) =
        service.delete(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) =
        service.update(id, promocao)

}