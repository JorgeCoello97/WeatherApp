package com.jorch.weatherapp.data

data class Weather(val id: String, val main: String, val description: String,
                   val icon: String)

data class Temperature(val day: Float, val min: Float, val max: Float,
                       val night: Float, val eve: Float, val morn: Float)

data class Coordinate(val log:Float, val lat:Float)

data class City(val id: Long, val name:String, val coord: Coordinate,
                val country:String, val populatation:Int)

data class Forecast(val dt: Long, val temp: Temperature, val pressure: Float,
                    val humidity: Int, val weather: List<Weather>,
                    val speed: Float, val deg: Int, val clouds: Int,
                    val rain: Float)

data class ForecastResult(val city: City, val list: List<Forecast>)