package com.smartpilates.mobile.model

import com.google.gson.annotations.SerializedName

data class ProfileModel(

    @SerializedName("id")
    val id:String,

    @SerializedName("name")
    val name:String,

    @SerializedName("surname")
    val surname:String,

    @SerializedName("email")
    val email:String,

    @SerializedName("phone")
    val phone:String,

    @SerializedName("passwd")
    val passwd:String,

    @SerializedName("meslek")
    val meslek:String,

    @SerializedName("gender")
    val gender:String,

    @SerializedName("address")
    val address:String,

    @SerializedName("detail")
    val detail:String,

    @SerializedName("bday")
    val birthDay:String,

    @SerializedName("bcity")
    val birthCity:String,

    @SerializedName("level")
    val level:String,

    @SerializedName("talep_list_json")
    val talep_list_json:String,

    @SerializedName("active")
    val active:String
) {
}