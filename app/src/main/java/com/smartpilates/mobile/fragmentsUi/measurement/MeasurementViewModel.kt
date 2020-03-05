package com.smartpilates.mobile.fragmentsUi.measurement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.DietModel
import com.smartpilates.mobile.repository.DataRepository

class MeasurementViewModel(application: Application):AndroidViewModel(application) {
    private  var sharedPRef: SharedPrfHelper = SharedPrfHelper(application)
    val dietListObservable:LiveData<ArrayList<DietModel>>

    init {
        dietListObservable=DataRepository.getDietList(sharedPRef.getUserID())
    }



}