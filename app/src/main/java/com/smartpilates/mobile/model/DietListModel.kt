package com.smartpilates.mobile.model

import com.google.gson.annotations.SerializedName

class DietListModel(
    @SerializedName("id")
    var id:String,

    @SerializedName("date_added")
    var date_added:String,

    @SerializedName("customer_id")
    var customer_id:String,

    @SerializedName("diyetisyen_id")
    var diyetisyen_id:String

)