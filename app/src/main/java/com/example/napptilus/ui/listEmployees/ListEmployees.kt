package com.example.napptilus.ui.listEmployees

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.napptilus.R
import com.example.napptilus.data.listEmployees.network.response.EmployeesListItem
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEmployees(viewModel: ListEmployeesViewModel, navController: NavController) {

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = true)
    val listEmployees: Array<EmployeesListItem> by viewModel.listEmployees.observeAsState(initial = arrayOf())
    val coroutineScope = rememberCoroutineScope()
    var dataLoaded by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(R.string.title_list_employees),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }, navigationIcon = {
            IconButton(onClick = { //TODO
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back"
                )
            }
        }, modifier = Modifier.fillMaxWidth(),

            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        )
    }) {
        coroutineScope.launch {
            if (!dataLoaded) {
                viewModel.onLoadingData()
                dataLoaded = true
            }
        }
        var modifier = Modifier
            .fillMaxHeight()
            .padding(it)
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        } else {
            BodyContentListEmployees(modifier, listEmployees)
        }
    }
}

@Composable
fun BodyContentListEmployees(modifier: Modifier, listEmployees: Array<EmployeesListItem>) {
    Box(
        modifier = modifier,
    ) {
        ItemList(elementos = listEmployees)
    }
}

@Composable
fun ItemList(elementos: Array<EmployeesListItem>) {
    LazyColumn {
        items(elementos) { item: EmployeesListItem ->
            Item(employees = item)
        }
    }
}

@Composable
fun Item(employees: EmployeesListItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(R.color.itemList_background))
            .padding(10.dp)
            .height(70.dp)
    ) {
        ImageProfile(employees.image)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextLabel(title = "Nombre", body = employees.first_name)
            TextLabel(title = "Apellido", body = employees.last_name)
            TextLabel(title = "Profesion", body = employees.profession)
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
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(
            text = title, fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 11.7.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Text(
            text = body, fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 11.7.sp,
            modifier = Modifier
                .weight(1f)
        )
    }
}

