package com.example.napptilus.ui.utils.toast


import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToastMessage(message: String, show: Boolean) {
    if(show) {
        Toast.makeText(
            LocalContext.current,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}