package com.example.napptilus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.napptilus.navigation.AppNavigation
import com.example.napptilus.ui.listEmployees.ListEmployeesViewModel
import com.example.napptilus.ui.theme.NapptilusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val listViewModel: ListEmployeesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NapptilusTheme {
                AppNavigation()
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppNavigation()
}