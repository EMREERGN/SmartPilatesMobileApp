package com.smartpilates.mobile.model


import com.google.gson.annotations.SerializedName

data class HaberlerModel(
    @SerializedName("created_at")
    val createdAt: String,
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