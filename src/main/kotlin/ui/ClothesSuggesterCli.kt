package org.example.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.example.domain.entities.ClothItem
import org.example.ui.io.ConsoleIO
import org.example.ui.utils.TextStyle

class ClothesSuggesterCli(
    private val io: ConsoleIO,
	private val presenter: ClothesSuggesterPresenter
) {
	private val clothesSuggesterCliScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    fun start() {
        printWelcome()
	    displayOptions()

	    val option = getUserOption()
	    do {
		    presenter.handleOptions(
			    option = option,
			    getCityAndCountryInput = { getUserCityAndCountry() },
			    onExit = {
				    io.printer.printText(
					    text = "Thanks for using WeatherClothesSuggester!",
					    textStyle = TextStyle.GOODBYE,
					    withNewLine = false
				    )
			    }
		    )

		} while (option != 0)

		clothesSuggesterCliScope.launch {
			presenter.clotheSuggesterUi
				.collect {
					if (it.isLoading) {
						io.printer.printText(
							text = "Loading...",
							textStyle = TextStyle.LOADER,
						)
						return@collect
					}
					if (it.error != null) {
						io.printer.printText(
							text = it.error,
							textStyle = TextStyle.ERROR,
							withNewLine = false
						)
						return@collect
					}
					displaySuggestions(it.suggestions)
				}
		}
    }

    private fun displayOptions() {
        io.printer.printText("╔════════════════════════════════════════════════════╗", TextStyle.OPTION)
        io.printer.printText("║          Athens Clothes Suggester App              ║", TextStyle.OPTION)
        io.printer.printText("╠════════════════════════════════════════════════════╣", TextStyle.OPTION)
        io.printer.printText("║  1.  Suggest Clothes Based On My City And Country  ║", TextStyle.OPTION)
        io.printer.printText("║  0.  Exit                                          ║", TextStyle.EXIT)
        io.printer.printText("╚════════════════════════════════════════════════════╝", TextStyle.OPTION)

    }

    private fun getStringInput(message: String): String {
        io.printer.printText(message, textStyle = TextStyle.INFO, withNewLine = false)
        return io.reader.readStringFromUser()
    }

    private fun getUserCityAndCountry(): Pair<String, String> {
        val city = getStringInput("Enter your city:")
        val country = getStringInput("Enter your country:")

	    return city to country
    }

    private fun printWelcome() {
        io.printer.printText(
	        text = "Welcome to the Athens Weather Clothes Suggester!",
	        textStyle = TextStyle.WELCOME,
	        withNewLine = true
        )
    }

    private fun getUserOption(): Int {
        io.printer.printText(
			text = "Please choose option : ", 
			textStyle = TextStyle.TITLE,
	        withNewLine = false
		)
        return io.reader.readNumberFromUser()
    }

    private fun displaySuggestions(clothes: List<ClothItem>) {
        io.printer.printText(
	        text = "Based on today’s weather, you should consider wearing:",
	        textStyle = TextStyle.TITLE,
        )

        clothes.forEach {
            io.printer.printText(
	            text = "- ${it.name} [${it.weight}, ${it.type}]",
	            textStyle = TextStyle.SUCCESS,
			)
        }
    }
}