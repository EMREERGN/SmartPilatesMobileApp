package com.smartpilates.mobile.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartpilates.mobile.model.*
import com.smartpilates.mobile.retrofit.IApi
import com.smartpilates.mobile.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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


        fun getProfile(userID: String):LiveData<ProfileModel>{
            val retrofit=RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val postUserModel= UserIDPostModel(userID)
            val data=MutableLiveData<ProfileModel>()

            api.postProfile(postUserModel).enqueue(object :Callback<ProfileModel>{
                override fun onFailure(call: Call<ProfileModel>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ProfileModel>,
                    response: Response<ProfileModel>
                ) {
                    data.value=response.body()
                }

            })
            return data
        }


        fun getDietInfoList(userID: String):LiveData<ArrayList<DietInfoModel>>{
            val retrofit=RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val postUserModel= UserIDPostModel(userID)
            val data=MutableLiveData<ArrayList<DietInfoModel>>()

            api.postDiet(postUserModel).enqueue(object :Callback<ArrayList<DietInfoModel>>{
                override fun onFailure(call: Call<ArrayList<DietInfoModel>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ArrayList<DietInfoModel>>,
                    response: Response<ArrayList<DietInfoModel>>
                ) {
                    data.value=response.body()
                }

            })
            return data
        }



        fun getDietList(userID: String):LiveData<ArrayList<DietListModel>>{
            val retrofit=RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val postUserModel= UserIDPostModel(userID)
            val data=MutableLiveData<ArrayList<DietListModel>>()

            api.postDietList(postUserModel).enqueue(object :Callback<ArrayList<DietListModel>>{
                override fun onFailure(call: Call<ArrayList<DietListModel>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ArrayList<DietListModel>>,
                    response: Response<ArrayList<DietListModel>>
                ) {
                    data.value=response.body()
                }

            })
            return data
        }


        fun getHaberler():LiveData<ArrayList<HaberlerModel>>{
            val retrofit=RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val data=MutableLiveData<ArrayList<HaberlerModel>>()

            api.getHaberler().enqueue(object :Callback<ArrayList<HaberlerModel>>{
                override fun onFailure(call: Call<ArrayList<HaberlerModel>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ArrayList<HaberlerModel>>,
                    response: Response<ArrayList<HaberlerModel>>
                ) {

                   data.value=response.body()
                }

            })

            return data
        }


        fun getHaberDetay(haberId:String):LiveData<ArrayList<HaberDetayModel>>{
            val retrofit=RetrofitClient.instance
            val api=retrofit.create(IApi::class.java)

            val postId=HaberDetayPostModel(haberId)
            val data=MutableLiveData<ArrayList<HaberDetayModel>>()


            api.postHaberDetay(postId).enqueue(object :Callback<ArrayList<HaberDetayModel>>{
                override fun onFailure(call: Call<ArrayList<HaberDetayModel>>, t: Throwable) {

                }

                override fun onResponse(call: Call<ArrayList<HaberDetayModel>>, response: Response<ArrayList<HaberDetayModel>>) {
                   data.value=response.body()
                }

            })

            return data
        }

    }

}