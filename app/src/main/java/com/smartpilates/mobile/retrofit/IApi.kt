package com.smartpilates.mobile.retrofit

import com.smartpilates.mobile.model.LoginGetDataModel
import com.smartpilates.mobile.model.LoginPostDataModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {

    @POST("sent/login")
    fun postData(@Body loginPostModel: LoginPostDataModel): Call<LoginGetDataModel>



}