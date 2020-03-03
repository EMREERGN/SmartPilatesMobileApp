package com.smartpilates.mobile.helpers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.smartpilates.mobile.LoginActivity
import com.smartpilates.mobile.MainActivity
import com.smartpilates.mobile.model.LoginGetDataModel
import com.smartpilates.mobile.model.LoginPostDataModel
import com.smartpilates.mobile.retrofit.IApi
import com.smartpilates.mobile.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitHelper {

    fun checkUserForNumberAndPassword(phoneNumber: String, password: String,context: Context) {

        Log.i("LOGIN_","$phoneNumber $password")

        val retrofit= RetrofitClient.instance
        val api=retrofit.create(IApi::class.java)

        val postData= LoginPostDataModel(phoneNumber,password)
        val call = api.postData(postData)

        call.enqueue(object: Callback<LoginGetDataModel> {
            override fun onFailure(call: Call<LoginGetDataModel>, t: Throwable) {
                //Toast.makeText(this@LoginActivity,"Talebiniz alınamadı, lütfen daha sonra tekrar deneyiniz.",Toast.LENGTH_LONG).show()
                Toast.makeText(context,t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<LoginGetDataModel>, response: Response<LoginGetDataModel>) {
                // User Control
                if(response.body()!!.status=="success"){

                    val user_id=response.body()!!.id
                    val userName=response.body()!!.namesurname
                    // save user info's at shared pref
                    // Shared Pref Save Phone and Number
                    val sharedPref=SharedPrfHelper(context)
                    sharedPref.saveLoginPref(phoneNumber,password,user_id,userName)

                    val intent= Intent(context, MainActivity::class.java)
                    intent.putExtra(LoginActivity.INTENT_USER_ID_KEY,response.body()!!.id)
                    intent.putExtra(LoginActivity.INTENT_USER_NAME_KEY,response.body()!!.namesurname)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)



                }else{
                    Log.i("LOGIN_",response.body().toString())
                    Toast.makeText(context,
                        "Talebiniz alınamadı, lütfen daha sonra tekrar deneyiniz.",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }


}