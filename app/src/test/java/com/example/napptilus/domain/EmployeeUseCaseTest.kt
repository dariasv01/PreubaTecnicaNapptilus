package com.example.napptilus.domain

import com.example.napptilus.data.Employee.EmployeeRepository
import com.example.napptilus.data.Employee.network.model.EmployeeModel
import com.example.napptilus.data.Employee.network.response.EmployeeResponse
import com.example.napptilus.data.listEmployees.network.response.Favorite
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class EmployeeUseCaseTest {


    @RelaxedMockK
    private lateinit var employeeRepository: EmployeeRepository
    lateinit var getEmployeeUseCase: EmployeeUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getEmployeeUseCase = EmployeeUseCase(employeeRepository)
    }

    @Test
    fun `when send id get call not successful`() = runBlocking {
        val employee = EmployeeModel(
            3,
            EmployeeResponse()
        )
//        Given
        coEvery { employeeRepository.getEmployee(300000) } returns employee
//        When
        val response = getEmployeeUseCase(300000)
//        Then
        coVerify(exactly = 1) { employeeRepository.getEmployee(300000) }

        assert(response == employee)
    }

    @Test
    fun `when send id get call successful`() = runBlocking {
        val employee = EmployeeModel(
            1,
            EmployeeResponse(
                last_name = "Karadzas",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/1.jpg",
                profession = "Developer",
                quota = "Lorem ipsum dolor sit amet,",
                first_name = "Marcy",
                country = "Loompalandia",
                age = 21,
                favorite = Favorite(
                    color = "red",
                    food = "Chocolat",
                    random_string = "mIEQ7PnwMfBjZb0tu0JExo",
                    song = "Oompa Loompas:\nOompa Loompa doomp"
                ),
                gender = "F",
                email = "mkaradzas1@visualengin.com"
            )
        )
//        Given
        coEvery { employeeRepository.getEmployee(1) } returns employee
//        When
        val response = getEmployeeUseCase(1)
//        Then
        coVerify(exactly = 1) { employeeRepository.getEmployee(1) }

        assert(response == employee)
    }
}