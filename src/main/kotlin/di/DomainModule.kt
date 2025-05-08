package org.example.di

import org.example.domain.useCases.GetClothesSuggestionsUseCase
import org.example.domain.useCases.strategies.ClothesSuggestionStrategy
import org.example.domain.useCases.strategies.RainClothesSuggestionStrategy
import org.example.domain.useCases.strategies.TemperatureClothesSuggestionStrategy
import org.example.domain.useCases.strategies.WindClothesSuggestionStrategy
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    single { GetClothesSuggestionsUseCase(get(), get(), get()) }

    single(named("Rain Clothes")) {
        RainClothesSuggestionStrategy()
    }
    single(named("Wind Clothes")) {
        WindClothesSuggestionStrategy()
    }
    single(named("Temperature Clothes")) {
        TemperatureClothesSuggestionStrategy()
    }

    single<List<ClothesSuggestionStrategy>> {
        listOf(
            get(named("Rain Clothes")),
            get(named("Wind Clothes")),
            get(named("Temperature Clothes"))
        )
    }

}