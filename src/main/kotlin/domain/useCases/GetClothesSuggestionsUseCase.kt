package org.example.domain.useCases

import org.example.domain.entities.ClothItem
import org.example.domain.entities.Location

class GetClothesSuggestionsUseCase {
    suspend operator fun invoke(location: Location): List<ClothItem> {
        return emptyList()
    }
}