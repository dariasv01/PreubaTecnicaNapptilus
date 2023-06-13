package com.example.napptilus.ui.listEmployees

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.napptilus.R
import com.example.napptilus.data.listEmployees.network.response.EmployeesListItem
import com.example.napptilus.ui.utils.dialog.FilterDialog
import com.example.napptilus.ui.utils.dialog.InfoDialog
import com.example.napptilus.ui.utils.loading.LoadingBox
import com.example.napptilus.ui.utils.toast.ShowToastMessage
import com.example.napptilus.ui.utils.topAppBar.TopAppBarArrowLeft
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//
fun ListEmployees(viewModel: ListEmployeesViewModel, navController: NavController) {

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = true)
    val listEmployees: Array<EmployeesListItem> by viewModel.listEmployees.observeAsState(initial = arrayOf())
    val coroutineScope = rememberCoroutineScope()
    var dataLoaded by remember { mutableStateOf(false) }
    val showAlert: Boolean by viewModel.showAlertInfo.observeAsState(initial = false)
    val showFilter: Boolean by viewModel.showFilter.observeAsState(initial = false)
    val showToast: Boolean by viewModel.showToast.observeAsState(initial = false)

    Scaffold(topBar = {
        TopAppBarArrowLeft(
            title = stringResource(R.string.title_list_employees), navController
        )
    }) {
        if (isLoading) {
            LoadingBox()
        }
        if (!dataLoaded && listEmployees.isEmpty()) {
            coroutineScope.launch {
                viewModel.onLoadingData()
            }
            dataLoaded = true
        }
        var modifier = Modifier
            .fillMaxHeight()
            .padding(it)
        BodyContentListEmployees(modifier, listEmployees, viewModel, navController)
        InfoDialog(show = showAlert,
            title = stringResource(R.string.msg_error_title),
            text = stringResource(R.string.msg_error_text),
            onDismiss = {
                viewModel.closeAlert()
            })
        FilterDialog(show = showFilter, viewModel = viewModel
        )
        ShowToastMessage(show = showToast, message = stringResource(R.string.msg_last_item))

    }
}

@Composable
fun BodyContentListEmployees(
    modifier: Modifier,
    listEmployees: Array<EmployeesListItem>,
    viewModel: ListEmployeesViewModel,
    navController: NavController
) {
    Box(
        modifier = modifier,
    ) {
        Column() {
            SearchBar(viewModel)
            ItemList(itemsList = listEmployees, viewModel, navController)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: ListEmployeesViewModel) {

    val searchText: String by viewModel.searchText.observeAsState(initial = "")
    Row(
        modifier = Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        OutlinedTextField(modifier = Modifier.fillMaxWidth(0.75f),
            value = searchText,
            shape = RoundedCornerShape(40.dp),
            onValueChange = {
                viewModel.onSearchTextChange(it)
            },
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.align(CenterVertically)
                )
            })


        IconButton(onClick = {
            viewModel.openFilter()
        }, modifier = Modifier.fillMaxWidth(0.75f)) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = Modifier.align(CenterVertically)
            )
        }
    }

}

@Composable
fun ItemList(
    itemsList: Array<EmployeesListItem>,
    viewModel: ListEmployeesViewModel,
    navController: NavController
) {
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn {
        items(itemsList) { item: EmployeesListItem ->
            Item(employees = item, viewModel, navController)
            val lastItemIndex = itemsList.size - 1
            if (itemsList.indexOf(item) == lastItemIndex) {
                onLastItemDisplayed(viewModel)
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun onLastItemDisplayed(viewModel: ListEmployeesViewModel) {
    val coroutineScope = rememberCoroutineScope()
    if (viewModel.currentValue.value?.codeStatusval == 1) {
        coroutineScope.launch {
            viewModel.nextPage()
        }
    }
}

@Composable
fun Item(
    employees: EmployeesListItem, viewModel: ListEmployeesViewModel, navController: NavController
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(colorResource(R.color.itemList_background))
        .padding(10.dp)
        .height(70.dp)
        .clickable {
            viewModel.seeEmployee(navController, employees.id)
        }) {
        ImageProfile(employees.image)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextLabel(
                title = stringResource(R.string.lbl_name), body = employees.first_name
            )
            TextLabel(
                title = stringResource(R.string.lbl_last_name), body = employees.last_name
            )
            TextLabel(
                title = stringResource(R.string.lbl_profession), body = employees.profession
            )
        }

    }
}

@Composable
fun ImageProfile(image: String) {
    Image(
        painter = rememberImagePainter(data = image),
        contentDescription = "Image Profile",
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(60.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TextLabel(title: String, body: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 11.7.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Text(
            text = body,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontSize = 11.7.sp,
            modifier = Modifier.weight(1f)
        )
    }
}