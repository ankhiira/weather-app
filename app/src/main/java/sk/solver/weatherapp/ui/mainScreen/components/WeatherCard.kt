package sk.solver.weatherapp.ui.mainScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sk.solver.weatherapp.models.WeatherResponse
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    weather: WeatherResponse?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            WeatherItem(
                label = "City",
                value = weather?.name
            )
            WeatherItem(
                label = "Current temperature",
                value = "${weather?.main?.temp?.roundToInt()}°C"
            )
            WeatherItem(
                label = "Feels like",
                value = "${weather?.main?.feels_like?.roundToInt()}°C"
            )
            WeatherItem(
                label = "Humidity",
                value = "${weather?.main?.humidity}%"
            )
            WeatherItem(
                label = "Wind speed",
                value = "${weather?.wind?.speed} m/s"
            )
        }
    }
}