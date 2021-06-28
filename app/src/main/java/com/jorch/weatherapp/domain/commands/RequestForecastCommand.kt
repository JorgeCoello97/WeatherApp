package com.jorch.weatherapp.domain.commands

import com.jorch.weatherapp.data.ForecastRequest
import com.jorch.weatherapp.domain.mappers.ForecastDataMapper
import com.jorch.weatherapp.domain.model.ForecastList

class RequestForecastCommand (private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}