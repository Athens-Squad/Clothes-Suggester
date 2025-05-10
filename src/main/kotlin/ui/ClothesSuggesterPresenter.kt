package org.example.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.domain.useCases.GetClothesSuggestionsUseCase
import org.example.ui.model.ClothSuggesterUi

class ClothesSuggesterPresenter(
	private val getClothesSuggestionsUseCase: GetClothesSuggestionsUseCase
) {
	private val clothesScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

	private val _clotheSuggesterUi = MutableStateFlow(ClothSuggesterUi())
	val clotheSuggesterUi = _clotheSuggesterUi.asStateFlow()

	fun loadClothesSuggestions(city: String, country: String) {
		_clotheSuggesterUi.value = _clotheSuggesterUi.value.copy(isLoading = true)
		clothesScope.launch {
			try {
				val suggestions = getClothesSuggestionsUseCase.execute(
					city = city,
					country = country
				)
				if (suggestions.isEmpty()) {
					_clotheSuggesterUi.value = _clotheSuggesterUi.value.copy(
						error = "No suggestions found",
						isLoading = false
					)
					return@launch
				}
				_clotheSuggesterUi.value = _clotheSuggesterUi.value.copy(
					suggestions = suggestions,
					isLoading = false,
					error = null
				)
			} catch (e: Exception) {
				_clotheSuggesterUi.value = _clotheSuggesterUi.value.copy(
					isLoading = false,
					error = e.message
				)
			}
		}
	}

	fun handleOptions(
		option: Int,
		getCityAndCountryInput: () -> Pair<String, String>,
		onSuccess: () -> Unit,
		onExit: () -> Unit
	) {
		when (option) {
			1 -> {
				val cityCountryPair = getCityAndCountryInput()
				loadClothesSuggestions(
					city = cityCountryPair.first,
					country = cityCountryPair.second
				)
				onSuccess()
			}

			0 -> {
				onExit()
			}
		}
	}
}