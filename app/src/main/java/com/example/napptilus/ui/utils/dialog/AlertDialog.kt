package com.example.napptilus.ui.utils.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.napptilus.R

@Composable
fun InfoDialog(show: Boolean, title: String, text: String, onDismiss: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = stringResource(R.string.btn_accept))
                }
            })
    }
}


