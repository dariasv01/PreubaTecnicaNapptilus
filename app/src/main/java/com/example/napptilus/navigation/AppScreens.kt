package com.example.napptilus.navigation

sealed class AppScreens(val route: String) {
    object Home : AppScreens("home_screen")
    object ListEmployees : AppScreens("list_employees_screen")
    object Employee : AppScreens("employee_screen/{id}") {
        fun createRoute(id: Int) = "employee_screen/$id"

    }
}
