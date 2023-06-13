package com.example.napptilus.data.Employee.network.model

import com.example.napptilus.data.Employee.network.response.EmployeeResponse

data class EmployeeModel(
    val codeStatusval: Int? = 0,
    val employee: EmployeeResponse? = EmployeeResponse()
)
