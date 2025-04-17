package com.kashyap.mvvm_3_0.presentation.screens.auth.login

sealed class LoginIntents() {
    class StartLogin() : LoginIntents()
}