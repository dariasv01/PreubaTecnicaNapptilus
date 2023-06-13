package com.example.napptilus.data.listEmployees

import com.example.napptilus.data.listEmployees.network.ListEmployeesService
import com.example.napptilus.data.listEmployees.network.model.ListEmployeesModel
import javax.inject.Inject

class ListEmployeesRepository @Inject constructor(private val api : ListEmployeesService){

    suspend fun getList(page: Int): ListEmployeesModel {
        return api.getListEmployees(page)
    }
}