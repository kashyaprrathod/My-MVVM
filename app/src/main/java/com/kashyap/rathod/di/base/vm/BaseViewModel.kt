package com.kashyap.rathod.di.base.vm

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    @JvmField
    val obrClick: MutableLiveData<View> = MutableLiveData()

    fun onClick(view: View) {
        obrClick.value = view
    }
}