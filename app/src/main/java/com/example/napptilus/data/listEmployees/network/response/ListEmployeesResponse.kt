package com.example.napptilus.data.listEmployees.network.response

import com.google.gson.annotations.SerializedName

data class ListEmployeesResponse(
    @SerializedName("current")
    val current: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: Array<EmployeesListItem>
)

data class EmployeesListItem(
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("favorite")
    val favorite: Favorite,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("profession")
    val profession: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int
)

data class Favorite(
    @SerializedName("color")
    val color: String,
    @SerializedName("food")
    val food: String,
    @SerializedName("random_string")
    val random_string: String,
    @SerializedName("song")
    val song: String
)