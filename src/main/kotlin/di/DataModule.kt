package org.example.di

import org.example.data.datasource.LocationDataSource
import org.example.data.datasource.WeatherDataSource
import org.example.data.datasource.remote.LocationDataSourceImpl
import org.example.data.datasource.remote.WeatherDataSourceImpl
import org.example.data.mapper.LocationMapper
import org.example.data.repositories.LocationRepositoryImpl
import org.example.data.repositories.WeatherRepositoryImpl
import org.example.domain.repositories.LocationRepository
import org.example.domain.repositories.WeatherRepository
import org.koin.dsl.module

val dataModule = module {
	single { LocationMapper() }
    single<LocationRepository> { LocationRepositoryImpl(
	    locationDataSource = get(),
	    locationMapper = get()
    ) }
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    single<LocationDataSource> {LocationDataSourceImpl()}
    single<WeatherDataSource> {WeatherDataSourceImpl()}
}