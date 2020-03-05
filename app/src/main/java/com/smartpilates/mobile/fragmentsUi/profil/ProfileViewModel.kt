package com.smartpilates.mobile.fragmentsUi.profil

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.ProfileModel
import com.smartpilates.mobile.repository.DataRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private  var sharedPRef: SharedPrfHelper = SharedPrfHelper(application)

    val profileDataObservable:LiveData<ProfileModel>
    init {
       profileDataObservable=DataRepository.getProfile(sharedPRef.getUserID())
    }

}