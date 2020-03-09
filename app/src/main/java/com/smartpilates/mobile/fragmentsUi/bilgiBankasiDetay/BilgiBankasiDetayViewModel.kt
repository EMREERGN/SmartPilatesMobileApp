package com.smartpilates.mobile.fragmentsUi.bilgiBankasiDetay

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.DietListModel
import com.smartpilates.mobile.model.HaberDetayModel
import com.smartpilates.mobile.repository.DataRepository

class BilgiBankasiDetayViewModel(application: Application):AndroidViewModel(application) {

    private val sharedPrfHelper=SharedPrfHelper(application)
    val haberDetayObservable: LiveData<ArrayList<HaberDetayModel>> = DataRepository.getHaberDetay(sharedPrfHelper.getHaberID())

}