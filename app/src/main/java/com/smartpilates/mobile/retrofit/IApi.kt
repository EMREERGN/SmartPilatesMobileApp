package com.smartpilates.mobile.retrofit

import com.smartpilates.mobile.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IApi {

    @POST("sent/login")
    fun postData(@Body loginPostModel: LoginPostDataModel): Call<LoginGetDataModel>

    @POST("get/lessons")
    fun postLessonData(@Body lessonPost: UserIDPostModel): Call<ArrayList<LessonsGetDataModel>>

    @POST("get/sales")
    fun postMemberSales(@Body userIdPostModel: UserIDPostModel): Call<ArrayList<MemberSalesModel>>

}