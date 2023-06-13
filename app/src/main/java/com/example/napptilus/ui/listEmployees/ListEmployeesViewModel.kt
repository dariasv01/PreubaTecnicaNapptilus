package com.example.napptilus.ui.listEmployees

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.napptilus.data.listEmployees.network.model.ListEmployeesModel
import com.example.napptilus.data.listEmployees.network.response.EmployeesListItem
import com.example.napptilus.domain.ListEmployeesUseCase
import com.example.napptilus.navigation.AppScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListEmployeesViewModel @Inject constructor(private val listEmployeesUseCase: ListEmployeesUseCase) :
    ViewModel() {

    private val _listEmployees = MutableLiveData<Array<EmployeesListItem>>()
    private val _listEmployeesCache = MutableLiveData<Array<EmployeesListItem>>()
    private val _currentValue = MutableLiveData<ListEmployeesModel?>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _currentPage = MutableLiveData<Int>()
    private val _showAlertInfo = MutableLiveData<Boolean>()
    private val _showToast = MutableLiveData<Boolean>()
    private val _showFilter = MutableLiveData<Boolean>()
    private val _filterGender = MutableLiveData<String>()
    private val _filterProfession = MutableLiveData<String>()
    private val _searchText = MutableLiveData<String>()

    val currentPage: LiveData<Int> = _currentPage
    val currentValue: LiveData<ListEmployeesModel?> = _currentValue
    val isLoading: LiveData<Boolean> = _isLoading
    val listEmployees: LiveData<Array<EmployeesListItem>> = _listEmployees
    val showAlertInfo: LiveData<Boolean> = _showAlertInfo
    val showToast: LiveData<Boolean> = _showToast
    val searchText: LiveData<String> = _searchText
    val showFilter: LiveData<Boolean> = _showFilter


    suspend fun onLoadingData() {
        _isLoading.value = true
        _currentPage.value = 1
        _currentValue.value = listEmployeesUseCase(_currentPage.value!!)
        if (_currentValue.value!!.codeStatusval == 3) {
            _showAlertInfo.value = true
        } else if (_currentValue.value!!.codeStatusval == 0) {
            _showToast.value = true
        }
        _listEmployees.value = _currentValue.value!!.employees
        _listEmployeesCache.value = _currentValue.value!!.employees
        _isLoading.value = false
    }

    suspend fun nextPage() {
        _currentPage.value = currentPage.value?.plus(1)
        val currentArray: Array<EmployeesListItem>? = _listEmployeesCache.value
        _currentValue.value = listEmployeesUseCase(_currentPage.value!!)
        if (_currentValue.value!!.codeStatusval == 3) {
            _showAlertInfo.value = true
        } else if (_currentValue.value!!.codeStatusval == 0) {
            _showToast.value = true
        } else {
            val newArray = currentArray?.plus(listEmployeesUseCase(_currentPage.value!!).employees)
            _listEmployeesCache.value = newArray!!
            if(!_filterGender.value.isNullOrEmpty()|| !_filterProfession.value.isNullOrEmpty()) filter() else if(_searchText.value != "") filterByName() else _listEmployees.value = newArray!!

        }
    }

    fun closeAlert() {
        _showAlertInfo.value = false
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        filterByName()
    }

    fun filterByName(){
        val filterListName = if (_searchText.value != "") _listEmployeesCache.value?.toList()
            ?.filter { it.first_name.contains(_searchText.value!!,true)} else _listEmployeesCache.value?.toList()
        _listEmployees.value = filterListName?.toTypedArray()
    }

    fun filter() {
        _isLoading.value = true
        val filterListGender = if (_filterGender.value != "") _listEmployeesCache.value?.toList()
            ?.filter { it.gender == _filterGender.value } else _listEmployeesCache.value?.toList()
        val filterListProfession = if (_filterProfession.value != "") filterListGender
            ?.filter { it.profession == _filterProfession.value } else filterListGender
        _listEmployees.value = filterListProfession?.toTypedArray()
        _showFilter.value = false
        _isLoading.value = false
    }

    fun onSelectGender(text: String) {
        _filterGender.value = text
    }

    fun onSelectProfession(text: String) {
        _filterProfession.value = text
    }

    fun seeEmployee(navController: NavController, id: Int) {
        navController.navigate(AppScreens.Employee.createRoute(id))
    }

    fun openFilter() {
        _showFilter.value = true
    }

    fun closeFilter() {
        _showFilter.value = false
    }

    fun clearFilter() {
        _filterGender.value = ""
        _filterProfession.value = ""
        _searchText.value = ""
        filter()
    }
}