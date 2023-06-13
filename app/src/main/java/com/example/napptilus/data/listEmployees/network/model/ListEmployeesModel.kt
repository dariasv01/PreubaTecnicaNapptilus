package com.example.napptilus.data.listEmployees.network.model

import com.example.napptilus.data.listEmployees.network.response.EmployeesListItem

data class ListEmployeesModel(
    val codeStatusval: Int,
    val employees: Array<EmployeesListItem>
)
