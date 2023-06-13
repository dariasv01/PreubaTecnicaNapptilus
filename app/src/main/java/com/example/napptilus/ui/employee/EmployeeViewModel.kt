package com.example.napptilus.ui.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.napptilus.data.Employee.network.model.EmployeeModel
import com.example.napptilus.data.Employee.network.response.EmployeeResponse
import com.example.napptilus.domain.EmployeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val employeeUseCase: EmployeeUseCase) :
    ViewModel() {
    private val _employeeData = MutableLiveData<EmployeeResponse>()
    val employeeData: MutableLiveData<EmployeeResponse> = _employeeData
    private val _currentValue = MutableLiveData<EmployeeModel?>()
    private val _showAlertInfo = MutableLiveData<Boolean>()
    val showAlertInfo: LiveData<Boolean> = _showAlertInfo
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun onLoadingData(idEmployee: Int) {
        _isLoading.value = true
        _currentValue.value = employeeUseCase(idEmployee)
        if (_currentValue.value!!.codeStatusval == 3) {
            _showAlertInfo.value = true
        } else {
            _employeeData.value = _currentValue.value!!.employee!!
        }

        _isLoading.value = false
    }
}