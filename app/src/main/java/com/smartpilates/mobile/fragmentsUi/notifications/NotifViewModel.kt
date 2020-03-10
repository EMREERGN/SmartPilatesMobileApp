package com.smartpilates.mobile.fragmentsUi.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartpilates.mobile.model.NotifResponseModel
import com.smartpilates.mobile.repository.DataRepository

class NotifViewModel(application: Application):AndroidViewModel(application) {
    val notifResponseObservable: LiveData<ArrayList<NotifResponseModel>> = DataRepository.getNotifications()

}