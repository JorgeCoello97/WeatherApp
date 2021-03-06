package com.jorch.weatherapp.domain.commands

import com.jorch.weatherapp.domain.datasource.ForecastProvider
import com.jorch.weatherapp.domain.model.Forecast
import com.jorch.weatherapp.domain.model.ForecastList

class RequestForecastCommand(
    private val zipCode: Long,
    private val forecastProvider: ForecastProvider = ForecastProvider()) :
    Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}