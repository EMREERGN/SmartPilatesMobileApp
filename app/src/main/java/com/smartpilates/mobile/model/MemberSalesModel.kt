package com.smartpilates.mobile.model

import com.google.gson.annotations.SerializedName

data class MemberSalesModel(
    @SerializedName("name")
    val name:String,
    @SerializedName("order_hour")
    val orderHour:String,
    @SerializedName("completed")
    val completed:String,
    @SerializedName("total_price")
    val totalPrice:String,
    @SerializedName("start_date")
    val startDate:String,
    @SerializedName("end_date")
    val endDate:String
) {
}