package com.example.napptilus.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.napptilus.R
import com.example.napptilus.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    Scaffold {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxHeight(1f) // Ocupa todo el espacio vertical disponible y sobresale
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(modifier = Modifier
                .padding(50.dp)
                .fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = colorResource(R.color.btn_background)),
                onClick = { navController.navigate(AppScreens.ListEmployees.route) }) {
                Text(
                    stringResource(R.string.btn_welcome),
                    color = colorResource(R.color.btn_text),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
