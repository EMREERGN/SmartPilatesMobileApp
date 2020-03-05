package com.smartpilates.mobile.fragmentsUi.memberSales

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartpilates.mobile.helpers.SharedPrfHelper
import com.smartpilates.mobile.model.MemberSalesModel
import com.smartpilates.mobile.repository.DataRepository

class MemberSalesViewModel(application: Application) : AndroidViewModel(application) {

    private  var sharedPRef: SharedPrfHelper = SharedPrfHelper(application)
    var memberSalesObservable:LiveData<ArrayList<MemberSalesModel>>
    init {
        memberSalesObservable =DataRepository.getMemberSales(sharedPRef.getUserID())
    }


}