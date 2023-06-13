package com.example.napptilus.domain

import com.example.napptilus.data.listEmployees.ListEmployeesRepository
import com.example.napptilus.data.listEmployees.network.model.ListEmployeesModel
import javax.inject.Inject

class ListEmployeesUseCase @Inject constructor(private val repository : ListEmployeesRepository){

    suspend operator fun invoke(page: Int): ListEmployeesModel {
        return repository.getList(page)
    }
}