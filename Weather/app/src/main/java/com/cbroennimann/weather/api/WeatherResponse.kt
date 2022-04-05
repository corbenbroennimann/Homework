package com.cbroennimann.weather.api

import com.cbroennimann.weather.data.WeatherData
import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("data")
    lateinit var weatherData: List<WeatherData>
}