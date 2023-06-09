package com.example.napptilus.data.listEmployees.network

import com.example.napptilus.data.listEmployees.network.response.ListEmployeesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListEmployeesClient {
    @GET("oompa-loompas")
    suspend fun getListEmployees(@Query("page") page: Int): Response<ListEmployeesResponse>
}