package com.kashyap.mvvm_3_0.ui.diff_utils.async_differ

import androidx.lifecycle.viewModelScope
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.di.remote.helper.Resource
import com.kashyap.mvvm_3_0.di.remote.helper.SingleLiveEvent
import com.kashyap.mvvm_3_0.di.remote.retrofit.ApiResponse
import com.kashyap.mvvm_3_0.di.ui.base.AppViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AsyncDifferConceptActivityVM @Inject constructor() : AppViewModel() {

    val arlUsers = mutableListOf<UserBean>()

    fun getDataSet(): ArrayList<UserBean> {
        return ArrayList<UserBean>().apply {
            arlUsers.forEach {
                add(it.getFreshInstance())
            }
        }
    }

    val obrUsersData = SingleLiveEvent<Resource<ApiResponse<ArrayList<UserBean>>>>()
    fun fetchUsers(page: Int) = viewModelScope.launch {
        val hqMap = HashMap<String, Any>()
        hqMap["page"] = page
        hqMap["per_page"] = 5
        apiRepository.fetchUsersData(hqMap).collect {
            obrUsersData.value = it
        }
    }
}