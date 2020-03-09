package com.smartpilates.mobile.model


import com.google.gson.annotations.SerializedName

data class HaberDetayModel(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("tags_cat")
    val tagsCat: String,
    @SerializedName("title")
    val title: String
)