package org.example.ui.model

import org.example.domain.entities.ClothItem

data class ClothSuggesterUi(
	val suggestions: List<ClothItem> = emptyList(),
	val isLoading: Boolean = false,
	val error: String? = null
)