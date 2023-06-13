package com.example.napptilus.data.Employee.network

import com.example.napptilus.data.Employee.network.model.EmployeeModel
import com.example.napptilus.data.Employee.network.response.EmployeeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeService @Inject constructor(private val employeeClient: EmployeeClient) {
    suspend fun getEmployee(id: Int): EmployeeModel {
        try {
            return withContext(Dispatchers.IO) {
                val response = employeeClient.getEmployee(id)
                response.body()!!
                val result: EmployeeModel
                if (!response.isSuccessful) {
                    result = EmployeeModel(employee = EmployeeResponse(), codeStatusval = 3)
                } else {
                    result = EmployeeModel(
                        employee = response.body(),
                        codeStatusval = 1
                    )
                }
                result
            }
        } catch (e: Exception) {
            return EmployeeModel(employee = EmployeeResponse(), codeStatusval = 3)

        }

    }
}