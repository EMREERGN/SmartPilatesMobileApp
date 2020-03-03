package com.smartpilates.mobile

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.smartpilates.mobile.helpers.MyDialogHelper
import com.smartpilates.mobile.helpers.RetrofitHelper
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.retrofit.IApi
import com.smartpilates.mobile.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Retrofit

class LoginActivity : AppCompatActivity() {

    private val myDialogHelper= MyDialogHelper(this)

    companion object {
        const val INTENT_USER_ID_KEY="user_id"
        const val INTENT_USER_STATUS_KEY="user_status"
        const val INTENT_USER_INFO_KEY="user_info"
        const val INTENT_USER_NAME_KEY="user_name"
        const val LOGIN_GET_DATA_RESPONSE="login_get_data_response"
    }


    private lateinit var retrofit: Retrofit
    private lateinit var api: IApi
    private lateinit var retrofitHelper: RetrofitHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        listeners()
        checkUserSharedPref()

    }
    private fun checkUserSharedPref() {

        val sharedPrefHelper= SharedPrfHelper(this)
        retrofitHelper.checkUserForNumberAndPassword(sharedPrefHelper.getPhoneNumber(),sharedPrefHelper.getPassword(),this)

    }

    private fun listeners() {

        btnGirisYap.setOnClickListener {
            // veri Tabanından kullanıcı kontrol edilir

            // null değilse
            if (edtPhoneNumber.text!=null || edtPassword.text!=null){
                val phoneNumber:String=edtPhoneNumber.text!!.trim().toString()
                val password:String=edtPassword.text!!.trim().toString()

                retrofitHelper.checkUserForNumberAndPassword(phoneNumber,password,this)


            }

        }
        loginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            btnGirisYap.isEnabled = isChecked
            if (isChecked){
                btnGirisYap.backgroundTintList= ColorStateList.valueOf(ContextCompat.getColor(this,R.color.colorAccent))
            }
            else{
                btnGirisYap.backgroundTintList=
                    ColorStateList.valueOf(ContextCompat.getColor(this,android.R.color.white))

            }
        }

        privacyLinkText.setOnClickListener {
            myDialogHelper.showPrivacyPolicyView()
        }

    }




    private fun init() {

        retrofitHelper= RetrofitHelper()
        retrofit= RetrofitClient.instance
        api=retrofit.create(IApi::class.java)


    }
}
