package com.smartpilates.mobile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartpilates.mobile.model.LessonsGetDataModel
import com.smartpilates.mobile.model.MemberSalesModel
import com.smartpilates.mobile.model.UserIDPostModel
import com.smartpilates.mobile.retrofit.IApi
import com.smartpilates.mobile.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Member
import kotlin.collections.ArrayList

class DataRepository {

    companion object{
        fun getLessonDatas (userID:String):LiveData<ArrayList<LessonsGetDataModel>>{
            val retrofit=RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val postLessonModel= UserIDPostModel(userID)
            val data=MutableLiveData<ArrayList<LessonsGetDataModel>>()

            api.postLessonData(postLessonModel).enqueue(object :Callback<ArrayList<LessonsGetDataModel>>{
                override fun onFailure(call: Call<ArrayList<LessonsGetDataModel>>, t: Throwable) {
                    data.value=ArrayList()
                }
                override fun onResponse(
                    call: Call<ArrayList<LessonsGetDataModel>>,
                    response: Response<ArrayList<LessonsGetDataModel>>
                ) {
                    /*// Gelecek Dersler İlk Başta Geçmiş Dersler en sonda sıralama sistemi
                    response.body()?.sortWith(Comparator { o1, o2 ->
                        o2.completed.compareTo(o1.completed)
                    })*/
                    data.value=response.body()
                }
            })

            return data
        }


        fun getMemberSales (userID:String):LiveData<ArrayList<MemberSalesModel>>{
            val retrofit=RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val postUserModel= UserIDPostModel(userID)
            val data=MutableLiveData<ArrayList<MemberSalesModel>>()

            api.postMemberSales(postUserModel).enqueue(object :Callback<ArrayList<MemberSalesModel>>{
                override fun onFailure(call: Call<ArrayList<MemberSalesModel>>, t: Throwable) {
                    data.value=ArrayList()
                }

                override fun onResponse(
                    call: Call<ArrayList<MemberSalesModel>>,
                    response: Response<ArrayList<MemberSalesModel>>
                ) {
                    data.value=response.body()
                }

            })
            return data
        }






    }

}