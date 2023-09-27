package sk.solver.weatherapp.ui.mainScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherItem(
    label: String,
    value: String?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "$label:",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value ?: "undefined",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}