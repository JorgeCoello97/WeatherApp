package com.jorch.weatherapp.domain.datasource

import com.jorch.weatherapp.domain.model.Forecast
import com.jorch.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}