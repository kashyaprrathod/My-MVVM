package com.kashyap.mvvm_3_0.ui.main

import androidx.lifecycle.ViewModelProvider
import com.kashyap.mvvm_3_0.R
import com.kashyap.mvvm_3_0.databinding.ActivityMainBinding
import com.kashyap.mvvm_3_0.di.ui.base.AppActivity
import com.kashyap.mvvm_3_0.ui.rv_list_custom_diff.ListAdapterCustomDiffUtilsConceptActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity<ActivityMainBinding, MainActivityVM>() {
    override fun createBinding(): Pair<Int, MainActivityVM> {
        return Pair(R.layout.activity_main, ViewModelProvider(this)[MainActivityVM::class.java])
    }

    override fun onActivityCreated() {
        binding.btnPaging.setOnClickListener {
            startActivity(ListAdapterCustomDiffUtilsConceptActivity.newIntent(this))
        }
    }
}