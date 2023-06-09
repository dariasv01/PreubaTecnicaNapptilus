package com.example.napptilus.ui.listEmployees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.napptilus.data.listEmployees.network.response.EmployeesListItem
import com.example.napptilus.data.listEmployees.network.response.ListEmployeesResponse
import com.example.napptilus.domain.ListEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class ListEmployeesViewModel @Inject constructor(private val listEmployeesUseCase: ListEmployeesUseCase): ViewModel() {

    private val _listEmployees = MutableLiveData<Array<EmployeesListItem>>()
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    val listEmployees: LiveData<Array<EmployeesListItem>> = _listEmployees
    suspend fun onLoadingData() {
        _isLoading.value = true
        _listEmployees.value = listEmployeesUseCase(1)!!
        _isLoading.value = false
    }
}