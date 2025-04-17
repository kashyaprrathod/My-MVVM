package com.match.watch.app.data.bean.connection

data class ConnectionDto(
    val type: State,
    val isConnected: Boolean = true
) {
    enum class State {
        CELLULAR, WIFI, ETHERNET, NO_INTERNET
    }
}