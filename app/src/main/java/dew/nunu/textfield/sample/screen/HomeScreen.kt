package dew.nunu.textfield.sample.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dew.nunu.textfield.sample.navigation.Filter
import dew.nunu.textfield.sample.navigation.Identifier
import dew.nunu.textfield.sample.navigation.Value

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate(Value) }) {
            Text(text = " String vs TextFieldValue")
        }
        Button(onClick = { navController.navigate(Filter) }) {
            Text(text = "OnValueChange")
        }
        Button(onClick = { navController.navigate(Identifier) }) {
            Text(text = "Identifier")
        }
    }
}