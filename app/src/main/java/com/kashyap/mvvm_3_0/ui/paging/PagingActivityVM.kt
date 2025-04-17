package com.kashyap.mvvm_3_0.ui.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.kashyap.mvvm_3_0.data.user.UserBean
import com.kashyap.mvvm_3_0.di.ui.base.AppViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PagingActivityVM @Inject constructor() : AppViewModel() {

    val arl = ArrayList<UserBean>()

    fun fetchData(): Flow<PagingData<UserBean>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                CustomPagingSource()
            }
        ).flow
            .map { paging ->
                paging.map { user ->
                    arl.add(user)
                    user
                }
            }.cachedIn(viewModelScope)
    }
}