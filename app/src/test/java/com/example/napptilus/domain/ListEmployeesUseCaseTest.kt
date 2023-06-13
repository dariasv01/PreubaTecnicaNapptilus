package com.example.napptilus.domain

import com.example.napptilus.data.listEmployees.ListEmployeesRepository
import com.example.napptilus.data.listEmployees.network.model.ListEmployeesModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class ListEmployeesUseCaseTest {
    @RelaxedMockK
    private lateinit var listEmployeeRepository: ListEmployeesRepository

    lateinit var getListEmployeeUseCase: ListEmployeesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getListEmployeeUseCase = ListEmployeesUseCase(listEmployeeRepository)
    }

    @Test
    fun `when send number page `() = runBlocking {
//        Given
        coEvery { listEmployeeRepository.getList(21) } returns ListEmployeesModel(3, emptyArray())
//        When
        val response = getListEmployeeUseCase(21)
//        Then
        coVerify(exactly = 1) { listEmployeeRepository.getList(21) }
    }
}