package com.smartpilates.mobile.fragmentsUi.dietList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.DietListModel
import com.smartpilates.mobile.model.LessonsGetDataModel
import com.smartpilates.mobile.repository.DataRepository

class DietListesiViewModel(application: Application):AndroidViewModel(application) {
    val dietListObservable:LiveData<ArrayList<DietListModel>>
    private  var sharedPRef: SharedPrfHelper = SharedPrfHelper(application)

    init {
        dietListObservable = DataRepository.getDietList(sharedPRef.getUserID())
    }



}