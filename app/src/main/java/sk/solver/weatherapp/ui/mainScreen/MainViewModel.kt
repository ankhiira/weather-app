package sk.solver.weatherapp.ui.mainScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import sk.solver.weatherapp.models.WeatherResponse
import sk.solver.weatherapp.repository.WeatherClient
import sk.solver.weatherapp.repository.WeatherClientBuilder

class MainViewModel : ViewModel() {

    private val _weather = mutableStateListOf<WeatherResponse?>(null)
    val weather: SnapshotStateList<WeatherResponse?> = _weather

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val weatherClient = WeatherClientBuilder
        .create(WeatherClient::class.java)

    suspend fun loadCities(
        searchValues: String
    ) {
        val cities = searchValues.split(",")

        _weather.clear()
        _isLoading.value = true

        val deferredResults = cities.map { city ->
            viewModelScope.async {
                if (city.isNotBlank()) {
                    loadCity(city)
                }
            }
        }

        deferredResults.forEach { deferred ->
            deferred.await()
        }

        _isLoading.value = false
    }

    private suspend fun loadCity(
        city: String
    ) {
        try {
            val weatherResponse = weatherClient.getWeather(
                city,
                "metric",
                WeatherClientBuilder.WEATHER_APP_ID
            )

            _weather.add(weatherResponse)
        } catch (e: Exception) {
            _error.value = e.message.toString()
        }
    }

    fun clearError() {
        _error.value = null
    }
}