package org.example.ui

import kotlinx.coroutines.runBlocking
import org.example.domain.entities.ClothItem
import org.example.domain.useCases.GetClothesSuggestionsUseCase
import org.example.domain.useCases.GetLocationByCityAndCountry
import org.example.ui.io.ConsoleIO

class ClothesSuggesterCli(
    private val io: ConsoleIO,
    private val getClothesSuggestionsUseCase: GetClothesSuggestionsUseCase,
    private val getLocationByCityAndCountry: GetLocationByCityAndCountry
) {
    fun start() = runBlocking {
        printWelcome()

        handleOptions()

        io.printer.printGoodbyeMessage("Thanks for using WeatherClothesSuggester!")
    }

    private fun displayOptions() {
        io.printer.printOption("╔════════════════════════════════════════════════════╗")
        io.printer.printOption("║          Athens Clothes Suggester App              ║")
        io.printer.printOption("╠════════════════════════════════════════════════════╣")
        io.printer.printOption("║  1.  Suggest Clothes Based On My City And Country  ║")
        io.printer.printOption("║  0.  Exit                                          ║")
        io.printer.printOption("╚════════════════════════════════════════════════════╝")

    }

    private fun getStringInput(message: String): String {
        io.printer.printInfoLine(message)
        return io.reader.readStringFromUser()
    }

    private suspend fun handleOptions() {
        displayOptions()

        while (true) {
            when(getUserOption()) {
                1 -> handleSuggestByCity()
                0 -> {
                    io.printer.printGoodbyeMessage("Thanks for using WeatherClothesSuggester!")
                    break
                }
            }
        }
    }

    private fun exit() {

    }

    private suspend fun handleSuggestByCity() {
        val city = getStringInput("Enter your city:")
        val country = getStringInput("Enter your country:")

        io.printer.printLoader("Fetching weather data for $city, $country...")
        val location = getLocationByCityAndCountry(city, country)

        val suggestions = getClothesSuggestionsUseCase(location)

        displaySuggestions(suggestions)
    }

    private fun printWelcome() {
        io.printer.printWelcomeMessage("Welcome to the Athens Weather Clothes Suggester!")
        io.printer.printDivider()
    }



    private fun getUserOption(): Int {
        io.printer.printOption("Please choose option : ", false)
        return io.reader.readNumberFromUser()
    }



    private fun displaySuggestions(clothes: List<ClothItem>) {
        if (clothes.isEmpty()) {
            io.printer.printInfoLine("No suitable clothing recommendations found.")
            return
        }

        io.printer.printCorrectOutput("Based on today’s weather, you should consider wearing:")
        clothes.forEach {
            io.printer.printPlainText("- ${it.name} [${it.weight}, ${it.type}, ${it.attribute}]")
        }
    }
}