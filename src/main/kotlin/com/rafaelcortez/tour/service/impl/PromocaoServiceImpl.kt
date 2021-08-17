package com.rafaelcortez.tour.service.impl

import com.rafaelcortez.tour.model.Promocao
import com.rafaelcortez.tour.service.PromocaoService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl: PromocaoService {

    companion object{
        val initialPromocoes = arrayOf(
            Promocao(1, "Maravilhosa viagem a Cancun", "Cancun", true, 7, 4200.99),
            Promocao(2, "Viagem Radical com escalada e rapel", "Nova Zelandia",  false, 12, 12000.00),
            Promocao(3, "Viagem espiritual", "Thailandia", false, 17, 15000.00),
            Promocao(4, "Viagem com a familia", "Gramado", false, 5, 3500.33)
        )
    }

    var promocoes = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))

    override fun getbyId(id: Long): Promocao? {
       return promocoes[id]
    }

    override fun getByLocal(local: String): List<Promocao> =
        promocoes.filter {
            it.value.local.contains(local, true)
        }.map (Map.Entry<Long, Promocao>:: value).toList()


    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        delete(id)
        create(promocao)
    }
}