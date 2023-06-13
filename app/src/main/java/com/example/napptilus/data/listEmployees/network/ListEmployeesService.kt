package com.example.napptilus.data.listEmployees.network

import com.example.napptilus.data.listEmployees.network.model.ListEmployeesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class ListEmployeesService @Inject constructor(private val listEmployeesClient: ListEmployeesClient) {
    suspend fun getListEmployees(page: Int): ListEmployeesModel {
        try {
            return withContext(Dispatchers.IO) {
                val response = listEmployeesClient.getListEmployees(page)
                val result: ListEmployeesModel
                if (!response.isSuccessful) {
                    result = ListEmployeesModel(employees = arrayOf(), codeStatusval = 3)
                } else if (page != response.body()?.current) {
                    result = ListEmployeesModel(employees = arrayOf(), codeStatusval = 0)
                } else {
                    result = ListEmployeesModel(
                        employees = response.body()!!.results,
                        codeStatusval = 1
                    )
                }
                result

            }
        }catch (e:TimeoutException){
            return ListEmployeesModel(employees = arrayOf(), codeStatusval = 3)
        }

    }
}