package com.example.napptilus.domain

import com.example.napptilus.data.Employee.EmployeeRepository
import com.example.napptilus.data.Employee.network.model.EmployeeModel
import javax.inject.Inject

class EmployeeUseCase @Inject constructor(private val repository : EmployeeRepository){
    suspend operator fun invoke(id: Int): EmployeeModel {
        return repository.getEmployee(id)
    }
}