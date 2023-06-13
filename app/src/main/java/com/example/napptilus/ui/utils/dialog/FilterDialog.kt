package com.example.napptilus.ui.utils.dialog

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.napptilus.R
import com.example.napptilus.data.listEmployees.network.model.SelectFilter
import com.example.napptilus.ui.listEmployees.ListEmployeesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(show: Boolean, viewModel: ListEmployeesViewModel) {
    if (show) {
        val selectGender = arrayOf(
            SelectFilter("", stringResource(R.string.lbl_selected_gender)),
            SelectFilter("M", stringResource(R.string.lbl_gender_M)),
            SelectFilter("M", stringResource(R.string.lbl_gender_F))
        )
        var selectProfession: Array<SelectFilter> =
            arrayOf(SelectFilter("", stringResource(R.string.lbl_selected_profession)))
        stringArrayResource(id = R.array.professionList).forEach {
            selectProfession = selectProfession.plus(SelectFilter(it, it))
        }
        var selectedGender by remember { mutableStateOf(selectGender[0]) }
        var selectedProfession by remember { mutableStateOf(selectProfession[0]) }

        Dialog(
            onDismissRequest = { viewModel.closeFilter() },
            content = {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray)) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(text = "Titulo")
                        Spacer(modifier = Modifier.height(16.dp))
                        Box() {
                            val context = LocalContext.current
                            var expandedGender by remember { mutableStateOf(false) }
                            Column() {
                                Text(text = stringResource(R.string.lbl_gender))
                                Spacer(modifier = Modifier.height(16.dp))
                                ExposedDropdownMenuBox(
                                    expanded = expandedGender,
                                    onExpandedChange = {
                                        expandedGender = !expandedGender
                                    }
                                ) {
                                    TextField(
                                        value = selectedGender.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedGender
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(
                                        expanded = expandedGender,
                                        onDismissRequest = { expandedGender = false }
                                    ) {
                                        selectGender.forEach { item ->
                                            DropdownMenuItem(
                                                text = { Text(text = item.value) },
                                                onClick = {
                                                    expandedGender = false
                                                    Toast.makeText(
                                                        context,
                                                        item.value,
                                                        Toast.LENGTH_SHORT
                                                    )
                                                        .show()
                                                    selectedGender = item
                                                }
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(text = stringResource(R.string.lbl_profession))
                                Spacer(modifier = Modifier.height(16.dp))
                                var expandedProfession by remember { mutableStateOf(false) }
                                ExposedDropdownMenuBox(
                                    expanded = expandedProfession,
                                    onExpandedChange = {
                                        expandedProfession = !expandedProfession
                                    }
                                ) {
                                    TextField(
                                        value = selectedProfession.value,
                                        onValueChange = {},
                                        readOnly = true,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandedProfession
                                            )
                                        },
                                        modifier = Modifier.menuAnchor()
                                    )

                                    ExposedDropdownMenu(
                                        expanded = expandedProfession,
                                        onDismissRequest = { expandedProfession = false }
                                    ) {
                                        selectProfession.forEach { item ->
                                            DropdownMenuItem(
                                                text = { Text(text = item.value) },
                                                onClick = {
                                                    expandedProfession = false
                                                    Toast.makeText(
                                                        context,
                                                        item.value,
                                                        Toast.LENGTH_SHORT
                                                    )
                                                        .show()
                                                    selectedProfession = item
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            TextButton(onClick = {
                                viewModel.onSelectGender(selectedGender.key)
                                viewModel.onSelectProfession(selectedProfession.key)
                                viewModel.filter()
                            }) {
                                Text(text = stringResource(R.string.btn_accept))
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            TextButton(onClick = {
                                viewModel.clearFilter()
                            }) {
                                Text(text = stringResource(R.string.btn_clear))
                            }
                        }
                    }
                }
            })
    }
}