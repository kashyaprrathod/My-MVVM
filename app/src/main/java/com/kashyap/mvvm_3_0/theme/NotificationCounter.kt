package com.kashyap.mvvm_3_0.theme

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotificationCounter(innerPadding: PaddingValues) {
    val count = remember { mutableStateOf(CounterModel()) }
    Column(Modifier.padding(innerPadding)) {
        NotificationCounterHandler(count.value.count) {
        }
        NotificationDetailsHeader(count.value.count)
    }
}

@Composable
fun NotificationCounterHandler(count: Int, increment: () -> Unit) {
    Log.e("TAG", "NotificationCounterHandler: main")
    Text(
        text = "Notification Tapped :: $count"
    )
    Button(onClick = {
        Log.e("TAG", "NotificationCounterHandler:")
        increment.invoke()
    }) {
        Log.e("TAG", "NotificationCounterHandler: push text")
        Text("Push Notification")
    }
    Log.e("TAG", "NotificationCounterHandler: increment")
}

@Composable
fun NotificationDetailsHeader(value: Int) {
    Log.e("TAG", "NotificationDetailsHeader:")
    Card {
        Text(text = "Notification Details pushed :: $value")
    }
}

@Composable
@Preview(showBackground = true)
fun NotificationCounterPreview() {
    NotificationCounter(PaddingValues())
}

data class CounterModel(
    var count: Int = 0
)