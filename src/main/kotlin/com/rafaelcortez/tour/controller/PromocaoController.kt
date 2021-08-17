package com.rafaelcortez.tour.controller

import com.rafaelcortez.tour.model.Promocao
import com.rafaelcortez.tour.service.PromocaoService
import com.rafaelcortez.tour.service.impl.PromocaoServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocao"])
class PromocaoController {

    @Autowired
    lateinit var service: PromocaoService

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) =
        service.getbyId(id)

    @GetMapping()
    fun getByLocal(@RequestParam(required = false, defaultValue = "") local: String) =
        service.getByLocal(local)

    @PostMapping()
    fun create(@RequestBody promocao: Promocao) =
        service.create(promocao)

    @DeleteMapping("/{id}")
    fun delete (@PathVariable id: Long) =
        service.delete(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) =
        service.update(id, promocao)

}