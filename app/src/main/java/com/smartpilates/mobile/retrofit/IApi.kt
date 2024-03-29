package com.smartpilates.mobile.retrofit

import android.database.Observable
import com.smartpilates.mobile.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IApi {

    @POST("sent/login")
    fun postData(@Body loginPostModel: LoginPostDataModel): Call<LoginGetDataModel>

    @POST("get/lessons")
    fun postLessonData(@Body lessonPost: UserIDPostModel): Call<ArrayList<LessonsGetDataModel>>

    @POST("get/sales")
    fun postMemberSales(@Body userIdPostModel: UserIDPostModel): Call<ArrayList<MemberSalesModel>>

    @POST("get/profile")
    fun postProfile(@Body userIdPostModel: UserIDPostModel): Call<ProfileModel>

    @POST("get/diyetInfo")
    fun postDiet(@Body userIdPostModel: UserIDPostModel): Call<ArrayList<DietInfoModel>>


    @POST("get/diyetList")
    fun postDietList(@Body userIdPostModel: UserIDPostModel): Call<ArrayList<DietListModel>>

    @GET("all/haberler")
    fun getHaberler(): Call<ArrayList<HaberlerModel>>

    @POST("get/haber")
    fun postHaberDetay(@Body postID: HaberDetayPostModel): Call<ArrayList<HaberDetayModel>>

    @POST("sent/contact")
    fun postMessage(@Body sendMessageModel: MessagePostModel):Call<MessageResponseModel>

    @GET("get/bildirimler")
    fun getNotifications(): Call<ArrayList<NotifResponseModel>>
}