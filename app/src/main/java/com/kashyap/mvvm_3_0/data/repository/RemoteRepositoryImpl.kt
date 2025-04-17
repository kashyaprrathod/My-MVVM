package com.kashyap.mvvm_3_0.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kashyap.mvvm_3_0.R
import com.kashyap.mvvm_3_0.data.model.user.UserDto
import com.kashyap.mvvm_3_0.data.remote.ApiInterface
import com.kashyap.mvvm_3_0.data.remote.ApiResponse
import com.kashyap.mvvm_3_0.data.remote.Resource
import com.kashyap.mvvm_3_0.di.MyApplication
import com.kashyap.mvvm_3_0.domain.model.UserBean
import com.kashyap.mvvm_3_0.domain.repositoiry.RemoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) : RemoteRepository {

    //Convert api error body
    private fun getAPIError(errorBody: ResponseBody): String {
        return try {
            val apiResponse: ApiResponse<Any> = Gson().fromJson(errorBody.string(), object : TypeToken<ApiResponse<Any>>() {}.type) as ApiResponse<Any>
            apiResponse.message
        } catch (e: Exception) {
            errorBody.string()
        }
    }

    private fun getNetworkErrorMessage(throwable: Throwable): String {
        var errMsg = MyApplication.instance.getString(R.string.error_found)
        throwable.printStackTrace()
        if (throwable is HttpException) {
            errMsg = getErrorMessage(throwable)
            if (throwable.code() == HttpURLConnection.HTTP_OK) MyApplication.instance.restartApp()
        } else if (throwable is SocketTimeoutException) {
            errMsg = MyApplication.instance.getString(R.string.timeout)
        } else if (throwable is IOException) {
            errMsg = MyApplication.instance.getString(R.string.network_error)
        } else {
            if (throwable.message != null) errMsg = throwable.message!!
        }
        return errMsg
    }

    private fun getErrorMessage(throwable: Throwable): String {
        return try {
            val httpException = throwable as HttpException
            val errorBody = httpException.response()!!.errorBody()
            var errMsg = MyApplication.instance.getString(R.string.error_found)
            if (errorBody != null) {
                val jsonObject = JSONObject(errorBody.string())
                errMsg = jsonObject.getString("error")
            }
            errMsg
        } catch (e: Exception) {
            (throwable as HttpException).message()
        }
    }

    private fun <DTO, RES> execute(getApi: () -> Response<ApiResponse<DTO>>, mapper: (ApiResponse<DTO>?) -> RES?): Flow<Resource<RES>> = flow {
        emit(Resource.loading())
        try {
            delay(10 * 1000)
            val response = getApi()
            if (response.isSuccessful) {
                emit(Resource.success(mapper(response.body()) ?: return@flow))
            } else {
                val errorMsg = getAPIError(response.errorBody() ?: return@flow)
                emit(Resource.warn(null, errorMsg))
            }
        } catch (e: Exception) {
            emit(Resource.error(null, getNetworkErrorMessage(e)))
        }
    }

    override fun getUsersData2(): Flow<Resource<UserBean>> {
        return execute<UserDto, UserBean>({ apiInterface.getUserData() }, { it?.data?.map() })
    }
}