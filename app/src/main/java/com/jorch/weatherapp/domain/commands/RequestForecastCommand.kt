package com.jorch.weatherapp.domain.commands

import com.jorch.weatherapp.data.server.ForecastRequest
import com.jorch.weatherapp.domain.mappers.ForecastDataMapper
import com.jorch.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}