package com.jorch.weatherapp.domain.commands

import com.jorch.weatherapp.domain.datasource.ForecastProvider
import com.jorch.weatherapp.domain.model.Forecast

class RequestDayForecastCommand(
    val id: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()
) :
    Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}