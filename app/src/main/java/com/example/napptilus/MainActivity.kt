package com.example.napptilus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.napptilus.navigation.AppNavigation
import com.example.napptilus.ui.theme.NapptilusTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NapptilusTheme {
                AppNavigation()
            }

        }
    }
}


