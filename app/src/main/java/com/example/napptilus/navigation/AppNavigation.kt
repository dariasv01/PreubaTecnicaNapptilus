package com.example.napptilus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.napptilus.ui.home.Home
import com.example.napptilus.ui.listEmployees.ListEmployees
import com.example.napptilus.ui.listEmployees.ListEmployeesViewModel

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Home.route){
        composable(route = AppScreens.Home.route){
            Home(navController)
        }
        composable(route = AppScreens.ListEmployees.route){
            val viewModelListEmployees = ListEmployeesViewModel()
            ListEmployees(viewModelListEmployees, navController)
        }
    }
}