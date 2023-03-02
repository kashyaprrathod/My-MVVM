package com.kashyap.rathod.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.kashyap.rathod.R
import com.kashyap.rathod.databinding.ActivityMainBinding
import com.kashyap.rathod.di.base.view.AppActivity
import com.kashyap.rathod.di.base.vm.BaseViewModel

class MainActivity : AppActivity<ActivityMainBinding, BaseViewModel>() {

    override fun createLayout(layoutData: (Int, BaseViewModel) -> Unit) {
        layoutData(R.layout.activity_main, ViewModelProvider(this)[MainActivityVM::class.java])
    }

    override fun observer() {

    }

    override fun init(savedInstanceState: Bundle?) {

    }
}