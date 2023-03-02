package com.kashyap.rathod.di.remote.help

abstract class ApiCallback<T> {
    abstract fun onLoading()
    abstract fun onSuccess(response: T)
    abstract fun onFailed(message: String)
    abstract fun onErrorThrow(exception: Exception)
}