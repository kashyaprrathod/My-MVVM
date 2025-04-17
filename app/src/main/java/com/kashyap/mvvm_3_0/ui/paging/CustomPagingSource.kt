package com.kashyap.mvvm_3_0.ui.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kashyap.mvvm_3_0.data.user.UserBean
import kotlinx.coroutines.delay

class CustomPagingSource : PagingSource<Int, UserBean>() {
    override fun getRefreshKey(state: PagingState<Int, UserBean>): Int? {
        if (state.anchorPosition != null) {
            val page = state.closestPageToPosition(state.anchorPosition!!)
            if (page?.prevKey != null) {
                val page = page.prevKey?.plus(1)
                Log.e("TAG", "getRefreshKey: prev key :: $page")
                return page
            } else if (page?.nextKey != null) {
                val page = page.nextKey?.minus(1)
                Log.e("TAG", "getRefreshKey: next key :: $page")
                return page
            }
        }
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserBean> {
        params
        delay(5 * 1000)
        val key = params.key ?: 0
        Log.e("TAG", "load: $key")
        val arl = ArrayList<UserBean>()
        for (i in 0 until 20) {
            arl.add(UserBean(id = i, firstName = "kaash$i"))
        }

        return LoadResult.Page(
            data = arl,
            prevKey = if (key == 0) null else key - 1,
            nextKey = if (key == 20) null else key + 1
        )
    }
}