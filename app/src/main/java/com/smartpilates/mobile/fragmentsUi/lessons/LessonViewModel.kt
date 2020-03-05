package com.smartpilates.mobile.fragmentsUi.lessons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.LessonsGetDataModel
import com.smartpilates.mobile.repository.DataRepository

class LessonViewModel (application: Application) : AndroidViewModel(application) {
    val lessonsListObservable:LiveData<ArrayList<LessonsGetDataModel>>
    private  var sharedPRef: SharedPrfHelper = SharedPrfHelper(application)

    init {
        lessonsListObservable = DataRepository.getLessonDatas(sharedPRef.getUserID())
    }

}