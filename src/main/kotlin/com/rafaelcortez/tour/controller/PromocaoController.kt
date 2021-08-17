package com.rafaelcortez.tour.controller

import com.rafaelcortez.tour.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
class PromocaoController {

    @Autowired
    lateinit var promocoes: ConcurrentHashMap<Long, Promocao>

    @RequestMapping(value = ["/sayHello"], method = arrayOf(RequestMethod.GET))
    fun sayHello(): String{
        return "Hello world"
    }

    @RequestMapping(value = ["/promocoes/{id}"], method = arrayOf(RequestMethod.GET))
    fun getById(@PathVariable id: Long) = promocoes[id]

    @RequestMapping(value = ["/promocoes"], method = arrayOf(RequestMethod.GET))
    fun getByLocal(@RequestParam(required = false, defaultValue = "") localFilter: String) =
        promocoes.filter {
            it.value.local.contains(localFilter, true)
        }.map (Map.Entry<Long, Promocao>:: value).toList()


    @RequestMapping(value = ["/promocoes"], method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody promocao: Promocao){
        promocoes[promocao.id] = promocao
    }

    @RequestMapping(value = ["/promocoes/{id}"], method = arrayOf(RequestMethod.DELETE))
    fun delete (@PathVariable id: Long) {
        promocoes.remove(id)
    }

    @RequestMapping(value = ["/promocoes/{id}"], method = arrayOf(RequestMethod.PUT))
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao){
        promocoes.remove(promocao.id)
        promocoes[id] = promocao
    }

}