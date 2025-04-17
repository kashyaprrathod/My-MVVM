package com.kashyap.mvvm_3_0.presentation.screens.auth.login

data class LoginState(
    val isLoggingIn: Boolean = false,

    val email: String = "",
    val password: String = "",
    val canLogin: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isRememberMeChecked: Boolean = false
)