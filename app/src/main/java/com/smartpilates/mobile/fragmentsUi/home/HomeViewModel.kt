package com.smartpilates.mobile.fragmentsUi.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartpilates.mobile.helpers.SharedPrfHelper

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPrfHelper=SharedPrfHelper(application)

    private val _userName = MutableLiveData<String>().apply {
        value = "Hoşgeldiniz Sayın, ${sharedPrfHelper.getUserNameSurname()}"
    }
    val userName: LiveData<String> = _userName


}