package com.example.napptilus.data.Employee

import com.example.napptilus.data.Employee.network.EmployeeService
import com.example.napptilus.data.Employee.network.model.EmployeeModel
import javax.inject.Inject

class EmployeeRepository @Inject constructor(private val api : EmployeeService){

    suspend fun getEmployee(id: Int): EmployeeModel {
        return api.getEmployee(id)
    }
}