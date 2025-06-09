package se.yverling.lab.cmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import se.yverling.lab.cmp.model.Country
import se.yverling.lab.cmp.model.countries
import se.yverling.lab.cmp.model.currentTimeAt
import se.yverling.lab.cmp.theme.DefaultSpace
import se.yverling.lab.cmp.theme.LargeSpace

private const val dropdownMenuWidthInPercent = 0.92f
private val imageSize = 50.dp

@Composable
fun MainScreen(
    countries: List<Country> = countries(),
    modifier: Modifier = Modifier,
) {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }

        val timeAtLocationDefaultValue = stringResource(Res.string.no_location_selected)
        var timeAtLocation by remember { mutableStateOf(timeAtLocationDefaultValue) }

        Column(modifier = modifier.padding(DefaultSpace)) {
            Text(
                text = timeAtLocation,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = DefaultSpace)
            )

            Box(
                modifier = Modifier
                    .padding(top = LargeSpace)
                    .fillMaxWidth()
            ) {

                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(dropdownMenuWidthInPercent),
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries.forEach { (name, zone, image) ->
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(image),
                                        modifier = Modifier.size(imageSize).padding(end = DefaultSpace),
                                        contentDescription = stringResource(Res.string.image_content_description)
                                    )

                                    Text(
                                        text = name,
                                        style = MaterialTheme.typography.titleMedium
                                    )
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
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { showCountries = !showCountries }) {
                    Text(text = stringResource(Res.string.button_label))
                }
            }
        }
    }
}
