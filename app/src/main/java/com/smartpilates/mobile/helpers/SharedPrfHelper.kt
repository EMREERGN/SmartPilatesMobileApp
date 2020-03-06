package com.smartpilates.mobile.helpers

import android.content.Context


class SharedPrfHelper(private val context: Context) {

    companion object {
        const val SHARED_PREF_KEY="com.smartplates.mobile.pref_key"

        const val PHONE_NUMBER_SHARED_PREF_KEY="SHARED_PREF_PHONE_NUMBER_SMART_PLATES"
        const val PASSWORD_SHARED_PREF_KEY="SHARED_PREF_PASSWORD_SMART_PLATES"
        const val USER_ID_SHARED_PREF_KEY="SHARED_PREF_USERID_SMART_PLATES"
        const val USER_NAME_SURNAME_SHARED_PREF_KEY="SHARED_PREF_USERNAME_SMART_PLATES"
        const val PORT_NUMBER_SHARED_PREF_KEY="SHARED_PREF_PORT_NUMBER_SMART_PLATES"
        const val IP_NUMBER_SHARED_PREF_KEY="SHARED_PREF_IP_NUMBER_SMART_PLATES"
        const val SHOW_NEVER_AGAIN_PERMISSION_SHARED_PREF_KEY="SHOW_NEVER_AGAIN_PERMISSION_SHARED_PREF_KEY"

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



    // port number
    fun setPortNumber(portNumber:Int){
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt(PORT_NUMBER_SHARED_PREF_KEY, portNumber)
            commit()
        }
    }
    fun getPortNumber(): Int {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getInt(PORT_NUMBER_SHARED_PREF_KEY,7800)
    }


    // İP ADRESSS
    fun setServerIpAdress(ip:String){
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(IP_NUMBER_SHARED_PREF_KEY, ip)
            commit()
        }
    }
    fun getServerIpAdress(): String? {
        val sharedPref = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
        return sharedPref.getString(IP_NUMBER_SHARED_PREF_KEY,"")
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
        return sharedPref.getString(USER_ID_SHARED_PREF_KEY,"")!!
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