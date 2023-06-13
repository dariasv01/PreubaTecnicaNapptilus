package com.example.napptilus.core.di

import com.example.napptilus.data.Employee.network.EmployeeClient
import com.example.napptilus.data.listEmployees.network.ListEmployeesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideListEmployeesCLient(retrofit: Retrofit):ListEmployeesClient{
        return retrofit.create(ListEmployeesClient::class.java)
    }
    @Singleton
    @Provides
    fun provideEmployeeClient(retrofit: Retrofit): EmployeeClient {
        return retrofit.create(EmployeeClient::class.java)
    }
}