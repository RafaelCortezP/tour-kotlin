package com.rafaelcortez.tour.service

import com.rafaelcortez.tour.model.Promocao

interface PromocaoService {

    fun getbyId(id: Long): Promocao?
    fun getByLocal(local: String): List<Promocao>
    fun create(promocao: Promocao)
    fun delete(id: Long)
    fun update(id: Long, promocao: Promocao)
}