package org.example

import org.example.di.dataModule
import org.example.di.domainModule
import org.example.di.uiModule
import org.example.ui.ClothesSuggesterCli
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

fun main() {
	startKoin {
		modules(dataModule, domainModule, uiModule)
	}

	val cli: ClothesSuggesterCli = getKoin().get()
	cli.start()
}