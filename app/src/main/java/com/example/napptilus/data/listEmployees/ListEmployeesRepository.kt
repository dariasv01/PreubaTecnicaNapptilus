package com.example.napptilus.data.listEmployees

import com.example.napptilus.data.listEmployees.network.ListEmployeesService
import com.example.napptilus.data.listEmployees.network.response.EmployeesListItem
import javax.inject.Inject

class ListEmployeesRepository @Inject constructor(private val api : ListEmployeesService){

    suspend fun getList(page: Int): Array<EmployeesListItem> {
        return api.getListEmployees(page)
    }
}