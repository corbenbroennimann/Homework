package com.cbroennimann.weather.api

import com.cbroennimann.weather.data.WeatherData
import retrofit2.Call
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather?&units=metric&APPID=API_KEY_HERE")
        fun getData(
        @Query("q") cityName: String
    ):  Single<WeatherData>

    fun fetchWeather():Call<WeatherData>
}