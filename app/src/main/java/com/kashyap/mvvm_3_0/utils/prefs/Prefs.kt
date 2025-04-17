package com.kashyap.mvvm_3_0.utils.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.kashyap.mvvm_3_0.BuildConfig
import com.kashyap.mvvm_3_0.di.MyApplication

object Prefs {

    private val prefs: SharedPreferences = MyApplication.Companion.appContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

    fun putString(key: String, value: String?) {
        prefs.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return prefs.getString(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        prefs.edit { putInt(key, value).apply() }
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return prefs.getInt(key, defaultValue)
    }

    fun putLong(key: String, value: Long) {
        prefs.edit { putLong(key, value).apply() }
    }

    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return prefs.getLong(key, defaultValue)
    }

    fun putFloat(key: String, value: Float) {
        prefs.edit { putFloat(key, value).apply() }
    }

    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return prefs.getFloat(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit { putBoolean(key, value).apply() }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return prefs.getBoolean(key, defaultValue)
    }

    fun putStringSet(key: String, value: Set<String>) {
        prefs.edit { putStringSet(key, value).apply() }
    }

    fun getStringSet(key: String, defaultValue: Set<String>? = emptySet()): Set<String>? {
        return prefs.getStringSet(key, defaultValue)
    }

    fun remove(key: String) {
        prefs.edit { remove(key) }
    }

    fun clear() {
        prefs.edit { clear() }
    }
}