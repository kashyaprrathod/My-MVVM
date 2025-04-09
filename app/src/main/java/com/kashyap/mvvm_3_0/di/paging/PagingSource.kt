package com.kashyap.mvvm_3_0.di.paging

//class PagingSource @Inject constructor() : PagingSource<Int, UserBean>() {
//
//    @Inject
//    lateinit var api: RepositoryImpl
//
//    override fun getRefreshKey(state: PagingState<Int, UserBean>): Int? {
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserBean> {
//        val page = params.key ?: 0
//        val hqMap = HashMap<String, Any>()
//        hqMap["page"] = page
//        hqMap["per_page"] = 5
//        api.fetchUsersData(hqMap).collect {
//            LoadResult.Page()
//        }
//    }
//}