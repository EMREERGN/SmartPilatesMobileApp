package com.smartpilates.mobile.fragmentsUi.home

import android.app.Application
import android.content.Context
import android.database.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.LessonsGetDataModel
import com.smartpilates.mobile.model.LessonsPostDataModel
import com.smartpilates.mobile.repository.DataRepository
import com.smartpilates.mobile.retrofit.IApi
import com.smartpilates.mobile.retrofit.RetrofitClient

class HomeViewModel(application: Application) : AndroidViewModel(application) {

   /* val lessonsListObservable:LiveData<ArrayList<LessonsGetDataModel>>
    private  var sharedPRef:SharedPrfHelper = SharedPrfHelper(application)

    init {
        lessonsListObservable = DataRepository.getLessonDatas(sharedPRef.getUserID())
    }*/


}