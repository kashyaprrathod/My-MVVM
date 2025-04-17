package com.kashyap.mvvm_3_0.presentation.screens.auth.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kashyap.mvvm_3_0.theme.black
import com.kashyap.mvvm_3_0.theme.fontFamilyPoppins
import com.kashyap.mvvm_3_0.theme.gray
import com.kashyap.mvvm_3_0.theme.gray2_20
import com.kashyap.mvvm_3_0.theme.red
import com.kashyap.mvvm_3_0.theme.transparent
import com.kashyap.mvvm_3_0.theme.white

@Composable
fun LoginScreen(vm: LoginVM = hiltViewModel()) {
    val loginState = vm.state.collectAsState()

    Column(
        modifier = Modifier
            .background(white)
            .padding()
            .fillMaxSize(1.0f)
            .padding(horizontal = 24.dp)
            .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
    ) {
        Box(
            modifier = Modifier.height(37.dp)
        )
        Text(
            text = "Welcome Back!",
            color = black,
            fontSize = 24.sp
        )
        Box(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = "Hello again, You’ve been missed!",
            color = gray,
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier.height(41.dp)
        )
        Text(
            text = "Email",
            color = black,
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier.height(5.dp)
        )
        TextField(
            value = loginState.value.email,
            textStyle = TextStyle(
                fontFamily = fontFamilyPoppins,
                fontWeight = FontWeight.W300
            ),
            onValueChange = {
                vm.changeData(loginState.value.copy(email = it))
            },
            placeholder = {
                Text(text = "Email", color = gray)
            },
            modifier = Modifier
                .fillMaxWidth(1.0f),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = gray2_20,
                focusedContainerColor = gray2_20,
                focusedTextColor = black,
                unfocusedIndicatorColor = transparent,
                focusedIndicatorColor = Color.Transparent,
            )
        )
        Box(
            modifier = Modifier.height(23.dp)
        )
        Text(
            text = "Password",
            color = black,
            fontSize = 14.sp
        )
        Box(
            modifier = Modifier.height(5.dp)
        )
        TextField(
            value = loginState.value.password,
            textStyle = TextStyle(
                fontFamily = fontFamilyPoppins,
                fontWeight = FontWeight.W700
            ),
            onValueChange = {
                vm.changeData(loginState.value.copy(password = it))
            },
            label = {
                Text("Password", color = gray)
            },
            trailingIcon = {
                Image(
                    imageVector = if (loginState.value.isPasswordVisible) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = "Password",
                    modifier = Modifier.clickable {
                        vm.changeData(loginState.value.copy(isPasswordVisible = loginState.value.isPasswordVisible.not()))
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth(1.0f),
            shape = RoundedCornerShape(10.dp),
            visualTransformation = if (loginState.value.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = gray2_20,
                focusedContainerColor = gray2_20,
                focusedTextColor = black,
                unfocusedIndicatorColor = transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(
            modifier = Modifier.height(9.dp)
        )
        Text(
            "Forget password?",
            color = red,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Row {
            Checkbox(
                checked = loginState.value.isRememberMeChecked,
                onCheckedChange = {
                    vm.changeData(loginState.value.copy(isRememberMeChecked = loginState.value.isRememberMeChecked.not()))
                },
                modifier = Modifier.padding(0.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = red
                )
            )
            Text(
                text = "Remember me",
                fontSize = 12.sp,
                color = black,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Box(
            modifier = Modifier
                .alpha(if (loginState.value.canLogin) 1.0f else 0.5f)
                .padding(top = 12.dp)
                .fillMaxWidth(1.0f)
                .height(45.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally)
                .background(red)
                .clickable {
                    vm.handleIntent(LoginIntents.StartLogin())
                },
        ) {
            Log.e("TAG", "LoginScreen: ${loginState.value.isLoggingIn}")
            if (loginState.value.isLoggingIn) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Text(
                    text = "Login",
                    color = white,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Spacer(Modifier.weight(1.0f))
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp)

        ) {
            Text(
                text = "Dont’have an account?",
                color = gray
            )
            Text(
                text = "Sign up",
                color = red,
                modifier = Modifier
                    .padding(start = 3.dp)
                    .clickable {},
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {

}