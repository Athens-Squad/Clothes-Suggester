package org.example.di

import org.example.ui.ClothesSuggesterCli
import org.example.ui.ClothesSuggesterPresenter
import org.example.ui.io.ConsoleIO
import org.example.ui.io.Printer
import org.example.ui.io.Reader
import org.example.ui.utils.Colors
import org.koin.dsl.module

val uiModule = module {

	single { Colors() }
	single { Printer(get()) }
	single { Reader() }
	single { ConsoleIO(get(), get()) }
	single { ClothesSuggesterPresenter(get()) }
	single { ClothesSuggesterCli(get(), get()) }
}