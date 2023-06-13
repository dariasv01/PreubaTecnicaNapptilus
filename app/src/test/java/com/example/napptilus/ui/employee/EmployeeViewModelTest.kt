package com.example.napptilus.ui.employee

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.napptilus.data.Employee.network.model.EmployeeModel
import com.example.napptilus.data.Employee.network.response.EmployeeResponse
import com.example.napptilus.data.listEmployees.network.response.Favorite
import com.example.napptilus.domain.EmployeeUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeViewModelTest {
    @RelaxedMockK
    private lateinit var employeeUseCasee: EmployeeUseCase

    private lateinit var viewModelEmployeeTest: EmployeeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModelEmployeeTest = EmployeeViewModel(employeeUseCasee)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when send id Employee get data Employee`() = runTest {
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
        coEvery { employeeUseCasee(1) } returns employee
//        When
        viewModelEmployeeTest.onLoadingData(1)
//        Then
        assert(viewModelEmployeeTest.employeeData.value == employee.employee)

    }
}