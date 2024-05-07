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
import dew.nunu.textfield.sample.navigation.Otp

@Composable
fun HomeScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate(Filter) }) {
            Text(text = "Filter Samples")
        }
        Button(onClick = { navController.navigate(Identifier) }) {
            Text(text = "Identifier Sample")
        }
        Button(onClick = { navController.navigate(Otp) }) {
            Text(text = " OTP Sample")
        }
    }
}