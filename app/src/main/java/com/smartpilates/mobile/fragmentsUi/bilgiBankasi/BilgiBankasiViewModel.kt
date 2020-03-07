package com.smartpilates.mobile.fragmentsUi.bilgiBankasi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.smartpilates.mobile.model.HaberlerModel
import com.smartpilates.mobile.repository.DataRepository

class BilgiBankasiViewModel(application: Application):AndroidViewModel(application) {

    val dietListObservable: LiveData<ArrayList<HaberlerModel>> = DataRepository.getHaberler()

}