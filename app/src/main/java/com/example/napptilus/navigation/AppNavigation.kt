package com.example.napptilus.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.napptilus.ui.employee.Employee
import com.example.napptilus.ui.employee.EmployeeViewModel
import com.example.napptilus.ui.home.Home
import com.example.napptilus.ui.home.HomeViewModel
import com.example.napptilus.ui.listEmployees.ListEmployees
import com.example.napptilus.ui.listEmployees.ListEmployeesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Home.route) {
        composable(route = AppScreens.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            Home(viewModel, navController)
        }
        composable(route = AppScreens.ListEmployees.route) {
            val viewModel: ListEmployeesViewModel = hiltViewModel()
            ListEmployees(viewModel, navController)
        }
        composable(
            route = AppScreens.Employee.route, arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            val viewModel: EmployeeViewModel = hiltViewModel()
            Employee(viewModel, navController, it.arguments?.getInt("id") ?: 0)
        }
    }
}