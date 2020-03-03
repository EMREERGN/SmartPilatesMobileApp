package com.smartpilates.mobile.model

import com.google.gson.annotations.SerializedName

data class LoginGetDataModel(
    @SerializedName("status")
    val status:String,
    @SerializedName("info")
    val info:String,
    @SerializedName("id")
    val id:String,
    @SerializedName("namesurname")
    val namesurname:String
)
{
}


data class LoginPostDataModel(
    @SerializedName("phone")
    val phone:String,
    @SerializedName("passwd")
    val passwd:String)
{
}