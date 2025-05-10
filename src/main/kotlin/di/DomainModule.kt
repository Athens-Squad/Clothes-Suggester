package org.example.di

import org.example.domain.useCases.GetClothesSuggestionsUseCase
import org.example.domain.useCases.strategies.ClothesSuggestionStrategy
import org.example.domain.useCases.strategies.RainClothesSuggestionStrategy
import org.example.domain.useCases.strategies.TemperatureClothesSuggestionStrategy
import org.example.domain.useCases.strategies.WindClothesSuggestionStrategy
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.math.sin

val domainModule = module {

    single<ClothesSuggestionStrategy>(named("Rain Clothes")) {
	    RainClothesSuggestionStrategy()
    }
	single<ClothesSuggestionStrategy>(named("Wind Clothes")) {
		WindClothesSuggestionStrategy()
	}
	single<ClothesSuggestionStrategy>(named("Temperature Clothes")) {
		TemperatureClothesSuggestionStrategy()
	}

    single<List<ClothesSuggestionStrategy>> {
	    listOf(
            get(named("Rain Clothes")),
            get(named("Wind Clothes")),
            get(named("Temperature Clothes"))
        )
    }
	single { GetClothesSuggestionsUseCase(get(), get(), get()) }
}