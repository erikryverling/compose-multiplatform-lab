package se.yverling.lab.kmp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.InternalResourceApi

data class Country(val name: String, val zone: TimeZone, val image: String)

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}

fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo"), "jp.png"),
    Country("France", TimeZone.of("Europe/Paris"), "fr.png"),
    Country("Mexico", TimeZone.of("America/Mexico_City"), "mx.png"),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), "id.png"),
    Country("Egypt", TimeZone.of("Africa/Cairo"), "eg.png")
)

@OptIn(InternalResourceApi::class)
@Composable
fun MainScreen(countries: List<Country> = countries()) {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("No location selected") }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                timeAtLocation,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {

                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries.forEach { (name, zone, image) ->
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(name)
                                    // TODO We need to read the png files from res somehow
//                                    Image(
//                                        painter = painterResource(),
//                                        modifier = Modifier.size(50.dp).padding(end = 16.dp),
//                                        contentDescription = "$name flag"
//                                    )
                                }
                            },

                            onClick = {
                                timeAtLocation = currentTimeAt(name, zone)
                                showCountries = false
                            },
                        )
                    }
                }

                Button(
                    onClick = { showCountries = !showCountries }) {
                    Text("Select Location")
                }
            }
        }
    }
}

// TODO Investigate if it's possible to Preview from commonMain
