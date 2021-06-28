package com.jorch.weatherapp.domain.mappers

import com.jorch.weatherapp.data.Forecast
import com.jorch.weatherapp.data.ForecastResult
import com.jorch.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.jorch.weatherapp.domain.model.Forecast as ModelForecast

class ForecastDataMapper {
    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }
    private fun generateIconUrl(iconCode: String): String = "https://openweathermap.org/img/w/$iconCode.png"

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon))
    }
    private fun convertForecastListToDomain(list: List<Forecast>) : List<ModelForecast>{
        return list.mapIndexed { index, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    fun convertFromDataModel(forecast: ForecastResult) : ForecastList =
        ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))


}