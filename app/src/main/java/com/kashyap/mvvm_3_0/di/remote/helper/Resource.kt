package com.kashyap.mvvm_3_0.di.remote.helper

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> warn(data: T?, message: String): Resource<T> =
            Resource(status = Status.WARN, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    WARN,
    LOADING
}