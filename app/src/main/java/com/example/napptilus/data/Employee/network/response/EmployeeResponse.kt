package com.example.napptilus.data.Employee.network.response

import com.example.napptilus.data.listEmployees.network.response.Favorite
import com.google.gson.annotations.SerializedName

data class EmployeeResponse(
    @SerializedName("first_name")
    val first_name: String? = "",
    @SerializedName("last_name")
    val last_name: String? = "",
    @SerializedName("favorite")
    val favorite: Favorite? = Favorite(),
    @SerializedName("gender")
    val gender: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("profession")
    val profession: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("age")
    val age: Int? = 0,
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("quota")
    val quota: String? = "",
    @SerializedName("description")
    val description: String? = ""
)
