package com.example.napptilus.data.listEmployees.network

import com.example.napptilus.core.network.RetrofitHelper
import com.example.napptilus.data.listEmployees.network.response.EmployeesListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListEmployeesService @Inject constructor(){
    val retrofit = RetrofitHelper.getRetrofit()
    suspend fun getListEmployees(page: Int): Array<EmployeesListItem> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ListEmployeesClient::class.java).getListEmployees(page)
            response.body()?.results ?: arrayOf()
        }
    }
}