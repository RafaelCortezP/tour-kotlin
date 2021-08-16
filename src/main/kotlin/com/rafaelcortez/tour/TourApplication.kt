package com.rafaelcortez.tour

import com.rafaelcortez.tour.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TourApplication{
	companion object{
		val initialPromocoes = arrayOf(
			Promocao(1, "Maravilhosa viagem a Cancun", "Cancun", true, 7, 4200.99),
			Promocao(2, "Viagem Radical com escalada e rapel", "Nova Zelandia",  false, 12, 12000.00),
			Promocao(3, "Viagem espiritual", "Thailandia", false, 17, 15000.00),
			Promocao(4, "Viagem com a familia", "Gramado", false, 5, 3500.33)
		)
	}

	@Bean
	fun promocoes() = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))

}

fun main(args: Array<String>) {
	runApplication<TourApplication>(*args)
}
