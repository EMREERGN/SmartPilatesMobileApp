package com.smartpilates.mobile.model

import com.google.gson.annotations.SerializedName

data class DietModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("customer_id")
    val customer_id: String,

    @SerializedName("boy")
    val boy: String,

    @SerializedName("kilo")
    val kilo: String,

    @SerializedName("gogus")
    val gogus: String,

    @SerializedName("kalca")
    val kalca: String,

    @SerializedName("karin")
    val karin: String,

    @SerializedName("bel")
    val bel: String,

    @SerializedName("basen")
    val basen: String,

    @SerializedName("sag_baldir")
    val sag_baldir: String,

    @SerializedName("sol_baldir")
    val sol_baldir: String,

    @SerializedName("sag_kol")
    val sag_kol: String,

    @SerializedName("sol_kol")
    val sol_kol: String,

    @SerializedName("omuz")
    val omuz: String,

    @SerializedName("yag_oran")
    val yag_oran: String,

    @SerializedName("kas_oran")
    val kas_oran: String,

    @SerializedName("detail")
    val detail: String,

    @SerializedName("diyetisyen_id")
    val diyetisyen_id: String,

    @SerializedName("created_at")
    val created_at: String,

    @SerializedName("sag_bacak")
    val sag_bacak: String,

    @SerializedName("sol_bacak")
    val sol_bacak: String

) {
}