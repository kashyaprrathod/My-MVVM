package com.kashyap.mvvm_3_0.ui.paging

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.kashyap.mvvm_3_0.R
import com.kashyap.mvvm_3_0.databinding.ActivityPagingBinding
import com.kashyap.mvvm_3_0.di.ui.base.AppActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PagingActivity : AppActivity<ActivityPagingBinding, PagingActivityVM>() {

    companion object {
        fun newIntent(c: Context): Intent {
            val intent = Intent(c, PagingActivity::class.java)
            return intent
        }
    }

    override fun createBinding(): Pair<Int, PagingActivityVM> {
        return Pair(R.layout.activity_paging, ViewModelProvider(this)[PagingActivityVM::class.java])
    }

    override fun onActivityCreated() {
        val adapter = UserPagingAdapter()
        binding.rv.adapter = adapter

        lifecycleScope.launch {
            vm.fetchData().collect {
                adapter.addData(this@PagingActivity.lifecycle, it)
            }
        }
    }
}