package com.kashyap.mvvm_3_0.ui.rv_list_custom_diff

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.kashyap.mvvm_3_0.R
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.databinding.ActivityListAdapterCustomDiffUtilsConceptBinding
import com.kashyap.mvvm_3_0.di.ui.base.AppActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class ListAdapterCustomDiffUtilsConceptActivity : AppActivity<ActivityListAdapterCustomDiffUtilsConceptBinding, ListAdapterCustomDiffUtilsConceptActivityVM>() {

    var adapter: ListCustomDiffUtilsConceptAdapter? = null

    companion object {
        fun newIntent(c: Context): Intent {
            val intent = Intent(c, ListAdapterCustomDiffUtilsConceptActivity::class.java)
            return intent
        }
    }

    override fun createBinding(): Pair<Int, ListAdapterCustomDiffUtilsConceptActivityVM> {
        return Pair(R.layout.activity_list_adapter_custom_diff_utils_concept, ViewModelProvider(this)[ListAdapterCustomDiffUtilsConceptActivityVM::class.java])
    }

    override fun onActivityCreated() {
        binding.btn.setOnClickListener {
            vm.arlUsers.add(
                UserBean(
                    id = Random.nextInt(5000000),
                    email = "kaash" + Random.nextInt() + "@gm.com"
                )
            )
            adapter?.addData(vm.getDataSet())
        }
        adapter = ListCustomDiffUtilsConceptAdapter({ item ->
            vm.arlUsers.removeIf {
                it.id == item.id
            }
            adapter?.addData(vm.getDataSet())
        }, { item ->
            vm.arlUsers.find {
                it.id == item.id
            }?.email = "kaaashyuuup@gmail.com"
            adapter?.addData(vm.getDataSet())
        })
        binding.rv.adapter = adapter
    }
}