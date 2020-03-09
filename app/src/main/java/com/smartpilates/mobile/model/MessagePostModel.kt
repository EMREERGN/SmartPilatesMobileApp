package com.smartpilates.mobile.model


import com.google.gson.annotations.SerializedName

data class MessagePostModel(
    @SerializedName("body")
    val body: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("userid")
    val userid: String
)

data class MessageResponseModel(
        @SerializedName("status")
        val status: String,
        @SerializedName("info")
        val info: String

)
