package com.smartpilates.mobile.model


import com.google.gson.annotations.SerializedName

data class NotifResponseModel(
    @SerializedName("cleandate")
    val cleandate: String,
    @SerializedName("day")
    val day: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("month")
    val month: String,
    @SerializedName("send_date")
    val sendDate: String,
    @SerializedName("title")
    val title: String
)