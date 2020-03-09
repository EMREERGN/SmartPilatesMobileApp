package com.smartpilates.mobile.helpers

import android.content.Context
import android.util.Log


class SharedPrfHelper(private val context: Context) {

    companion object {
        private const val TAG="Shared_Pref_Tag"


        const val SHARED_PREF_KEY="com.smartplates.mobile.pref_key"

        const val PHONE_NUMBER_SHARED_PREF_KEY="SHARED_PREF_PHONE_NUMBER_SMART_PLATES"
        const val PASSWORD_SHARED_PREF_KEY="SHARED_PREF_PASSWORD_SMART_PLATES"
        const val USER_ID_SHARED_PREF_KEY="SHARED_PREF_USERID_SMART_PLATES"
        const val USER_NAME_SURNAME_SHARED_PREF_KEY="SHARED_PREF_USERNAME_SMART_PLATES"
        const val SHOW_NEVER_AGAIN_PERMISSION_SHARED_PREF_KEY="SHOW_NEVER_AGAIN_PERMISSION_SHARED_PREF_KEY"
        const val HABER_ID_SHARED_PREF_KEY="HABER_ID_PREF_KEY"

    }

    // NEVER Again Check
    fun setPermissionNeverShowAgain(isCheck: Boolean) {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean(SHOW_NEVER_AGAIN_PERMISSION_SHARED_PREF_KEY, isCheck)
            commit()
        }

    }

    // NEVER Again Check
    fun getPermissionNeverShowAgain(): Boolean {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(SHOW_NEVER_AGAIN_PERMISSION_SHARED_PREF_KEY,false)
    }


    // haber Detay Id
    fun setHaberId(haberID:String){
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(HABER_ID_SHARED_PREF_KEY, haberID)
            commit()
        }
    }
    fun getHaberID(): String {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(HABER_ID_SHARED_PREF_KEY,"null")!!
    }



    fun getPhoneNumber(): String {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(PHONE_NUMBER_SHARED_PREF_KEY,"")!!
    }


    fun getPassword(): String {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(PASSWORD_SHARED_PREF_KEY,"")!!
    }
    fun getUserID(): String {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        val userId= sharedPref.getString(USER_ID_SHARED_PREF_KEY,"")!!
        Log.i(TAG,userId)
        return userId
    }
    fun getUserNameSurname(): String {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(USER_NAME_SURNAME_SHARED_PREF_KEY,"")!!
    }

    fun saveLoginPref(
        phoneNumber: String,
        password: String,
        user_id: String,
        userName: String
    ){
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(PHONE_NUMBER_SHARED_PREF_KEY, phoneNumber)
            commit()
        }
        with (sharedPref.edit()) {
            putString(PASSWORD_SHARED_PREF_KEY, password)
            commit()
        }
        with (sharedPref.edit()) {
            putString(USER_ID_SHARED_PREF_KEY, user_id)
            commit()
        }
        with (sharedPref.edit()) {
            putString(USER_NAME_SURNAME_SHARED_PREF_KEY, userName)
            commit()
        }
    }



}