package com.example.napptilus.data.Employee.network

import com.example.napptilus.data.Employee.network.response.EmployeeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeClient {
    @GET("oompa-loompas/{id}")
    suspend fun getEmployee(@Path("id") id: Int): Response<EmployeeResponse>
}