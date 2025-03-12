package com.kashyap.mvvm2.utils.services

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.kashyap.mvvm2.R
import com.kashyap.mvvm2.data.AppConst
import com.kashyap.mvvm2.ui.main.MainActivity
import com.kashyap.mvvm2.utils.notification.NotificationUtils
import com.kashyap.mvvm2.utils.prefs.Prefs
import kotlin.random.Random

class FireBaseMessageService : FirebaseMessagingService() {

    private val tag = "Messages"

    override fun onNewToken(s: String) {
        Prefs.putString(AppConst.PrefsKeys.FIREBASE_MESSAGE_TOKEN, s)
        Log.e("FirebaseToken", "The token refreshed $s")
        super.onNewToken(s)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val gcmRec = Intent("NotificationReceived")
        LocalBroadcastManager.getInstance(baseContext).sendBroadcast(gcmRec)
        Log.e(tag, " ${Gson().toJson(remoteMessage)}")
        selectPushType(remoteMessage)
    }


    private fun selectPushType(remoteMessage: RemoteMessage) {
        var title = baseContext.getString(R.string.app_name)
        var body = baseContext.getString(R.string.app_name)

        if (remoteMessage.data["title"] != null)
            title = remoteMessage.data["title"].toString()

        if (remoteMessage.data["body"] != null)
            body = remoteMessage.data["body"].toString()


        NotificationUtils().sendNotification(
            baseContext,
            title,
            body,
            getNotificationIntent(remoteMessage),
            Random.nextInt(5000)
        )
    }

    private fun getNotificationIntent(remoteMessage: RemoteMessage): Intent {
        try {
            return if (!remoteMessage.data["type"].isNullOrEmpty()) {
                when (remoteMessage.data["type"]?.toInt()) {

                    else -> {
                        MainActivity.newIntent(baseContext)
                    }
                }
            } else {
                MainActivity.newIntent(baseContext)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return MainActivity.newIntent(baseContext)
        }
    }
}