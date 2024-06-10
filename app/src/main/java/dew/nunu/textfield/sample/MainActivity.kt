package dew.nunu.textfield.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dew.nunu.textfield.sample.navigation.Filter
import dew.nunu.textfield.sample.navigation.Home
import dew.nunu.textfield.sample.navigation.Identifier
import dew.nunu.textfield.sample.navigation.Value
import dew.nunu.textfield.sample.screen.HomeScreen
import dew.nunu.textfield.sample.screen.IdentifierScreen
import dew.nunu.textfield.sample.screen.TextChangedFilterSample
import dew.nunu.textfield.sample.screen.TextFieldValueScreen
import dew.nunu.textfield.sample.ui.theme.TextFieldSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TextFieldSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        navController = navController,
                        startDestination = Home
                    ) {
                        composable<Home> {
                            HomeScreen(navController = navController)
                        }
                        composable<Filter> {
                            TextChangedFilterSample()
                        }
                        composable<Identifier> {
                            IdentifierScreen()
                        }
                        composable<Value> {
                            TextFieldValueScreen()
                        }
                    }
                }
            }
        }
    }
}
