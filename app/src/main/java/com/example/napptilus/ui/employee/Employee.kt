package com.example.napptilus.ui.employee

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.napptilus.R
import com.example.napptilus.data.Employee.network.response.EmployeeResponse
import com.example.napptilus.data.listEmployees.network.response.Favorite
import com.example.napptilus.ui.utils.dialog.InfoDialog
import com.example.napptilus.ui.utils.loading.LoadingBox
import com.example.napptilus.ui.utils.topAppBar.TopAppBarArrowLeft
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterial3Api
@Composable
fun Employee(viewModel: EmployeeViewModel, navController: NavController, idEmployee: Int) {
    val coroutineScope = rememberCoroutineScope()
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = true)
    val showAlert: Boolean by viewModel.showAlertInfo.observeAsState(initial = false)
    val employeeData: EmployeeResponse by viewModel.employeeData.observeAsState(
        initial = EmployeeResponse()
    )
    Scaffold(topBar = {
        TopAppBarArrowLeft(
            title = employeeData.first_name ?: "",
            navController
        )
    }) {
        val scrollState = rememberScrollState()
        var modifier = Modifier
            .fillMaxHeight()
            .padding(it)
            .verticalScroll(scrollState)
        if(isLoading){
            coroutineScope.launch {
                viewModel.onLoadingData(idEmployee)
            }
            LoadingBox()
        }else if (showAlert){
            InfoDialog(show = showAlert,
                title = stringResource(R.string.msg_error_title),
                text = stringResource(R.string.msg_error_text),
                onDismiss = {
                    navController.popBackStack()
                })
        }else{
            BodyContentEmployee(modifier, employeeData)
        }
    }
}

@Composable
fun BodyContentEmployee(
    modifier: Modifier,
    employeeData: EmployeeResponse
) {
    Column(
        modifier = modifier,
    ) {
        ImageProfileEmployee(employeeData.image ?: "")
        PersonalData(employeeData)
        LongText(
            title = stringResource(R.string.lbl_description),
            body = employeeData.description ?: ""
        )
        LongText(title = stringResource(R.string.lbl_quota), body = employeeData.quota ?: "")
    }
}

@Composable
fun ImageProfileEmployee(image: String) {
    Image(
        painter = rememberImagePainter(data = image),
        contentDescription = "Image Profile",
        modifier = Modifier
            .padding(15.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(175.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun LongText(title: String, body: String) {

    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(R.color.itemList_background))
            .padding(10.dp)
            .clickable {
                expanded = !expanded
            },
    ) {
        Column() {
            Text(
                text = title, fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 11.7.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = body, fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 11.7.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp),
            )
            if (!expanded && Int.MAX_VALUE > 3) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.lbl_read_more), fontWeight = FontWeight.Normal,
                    color = Color.Blue,
                    fontSize = 11.7.sp,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp),
                )
            }
        }
    }
}

@Composable
fun PersonalData(employeeData: EmployeeResponse) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(R.color.itemList_background))
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        TextLabelEmail(
            title = stringResource(R.string.lbl_email),
            body = employeeData.email ?: ""
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                TextLabelPersonalData(
                    title = stringResource(R.string.lbl_name),
                    body = employeeData.first_name ?: ""
                )
                TextLabelPersonalData(
                    title = stringResource(R.string.lbl_last_name),
                    body = employeeData.last_name ?: ""
                )
                TextLabelPersonalData(
                    title = stringResource(R.string.lbl_profession),
                    body = employeeData.profession ?: ""
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                TextLabelPersonalData(
                    title = stringResource(R.string.lbl_age),
                    body = employeeData.age.toString() ?: "0"
                )
                TextLabelPersonalData(
                    title = stringResource(R.string.lbl_county),
                    body = employeeData.country ?: ""
                )
                TextLabelPersonalData(
                    title = stringResource(R.string.lbl_gender),
                    body = if (employeeData.gender == "M") stringResource(R.string.lbl_gender_M) else stringResource(
                        R.string.lbl_gender_F
                    )
                )
            }
        }
    }


}

@Composable
fun TextLabelPersonalData(title: String, body: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = title, fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 11.7.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f),
        )
        Text(
            text = body, fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontSize = 11.7.sp,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
        )
    }
}

@Composable
fun TextLabelEmail(title: String, body: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = title, fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 11.7.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(0.5f),
        )
        Text(
            text = body, fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontSize = 11.7.sp,
            modifier = Modifier
                .weight(1.5f)
                .padding(end = 8.dp),
        )
    }
}

@Preview
@Composable
fun Preview() {

}