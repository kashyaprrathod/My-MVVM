package com.kashyap.mvvm_3_0.ui.diff_utils.async_differ

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.kashyap.mvvm_3_0.R
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.databinding.ActivityListAdapterConceptBinding
import com.kashyap.mvvm_3_0.di.ui.base.AppActivity
import com.kashyap.mvvm_3_0.ui.diff_utils.list_adapter.ListAdapterConceptActivityVM
import com.kashyap.mvvm_3_0.utils.loggerE
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class AsyncDifferConceptActivity : AppActivity<ActivityListAdapterConceptBinding, ListAdapterConceptActivityVM>() {

    var adapter: AsyncDifferConceptAdapter? = null

    companion object {
        fun newIntent(c: Context): Intent {
            val intent = Intent(c, AsyncDifferConceptActivity::class.java)
            return intent
        }
    }

    override fun createBinding(): Pair<Int, ListAdapterConceptActivityVM> {
        return Pair(R.layout.activity_list_adapter_concept, ViewModelProvider(this)[ListAdapterConceptActivityVM::class.java])
    }

    override fun onActivityCreated() {
        binding.btn.setOnClickListener {
            vm.arlUsers.add(
                UserBean(
                    id = Random.nextInt(5000000),
                    email = "Asynckaash" + Random.nextInt() + "@gm.com"
                )
            )
            adapter?.addData(vm.getDataSet())
        }
        adapter = AsyncDifferConceptAdapter({ item ->
            vm.arlUsers.removeIf {
                it.id == item.id
            }
            adapter?.addData(vm.getDataSet())
        }, { item ->
            loggerE(
                "edit ::: ${
                    vm.arlUsers.find {
                        it.id == item.id
                    }?.email
                }"
            )
            vm.arlUsers.find {
                it.id == item.id
            }?.email = "Kaashyap@Asyncgmail.com"
            loggerE(
                "edit ::: ${
                    vm.arlUsers.find {
                        it.id == item.id
                    }?.email
                }"
            )
            adapter?.addData(vm.getDataSet())
        })
        binding.rv.adapter = adapter
    }
}