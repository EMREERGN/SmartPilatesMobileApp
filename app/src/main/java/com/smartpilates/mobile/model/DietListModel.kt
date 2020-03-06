package com.smartpilates.mobile.model

import com.google.gson.annotations.SerializedName

class DietListModel(
    @SerializedName("id")
    val id:String,

    @SerializedName("date_added")
    val date_added:String,

    @SerializedName("customer_id")
    val customer_id:String,

    @SerializedName("diyetisyen_id")
    val diyetisyen_id:String

)