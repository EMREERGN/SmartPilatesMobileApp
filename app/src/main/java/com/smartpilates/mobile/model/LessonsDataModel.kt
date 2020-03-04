package com.smartpilates.mobile.model

import com.google.gson.annotations.SerializedName

data class LessonsGetDataModel(
    @SerializedName("id")
    val id:String,
    @SerializedName("lesson_date")
    val lesson_date:String,
    @SerializedName("lesson_time")
    val lesson_time:String,
    @SerializedName("lesson_no")
    val lesson_no:String,
    @SerializedName("completed")
    val completed:String,
    @SerializedName("name")
    val name:String,
    @SerializedName("title")
    val title:String
    ) {
}

data class LessonsPostDataModel(
    @SerializedName("user_id")
    val user_id:String
) {
}