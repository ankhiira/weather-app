package sk.solver.weatherapp.repository

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import sk.solver.weatherapp.models.WeatherResponse

interface WeatherClient {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cities: String?,
        @Query("units") units: String?,
        @Query("appid") appId: String?
    ): WeatherResponse
}