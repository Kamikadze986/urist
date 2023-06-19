package com.example.urist1.authManager

import android.content.Context
import android.content.Context.MODE_PRIVATE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class AuthorizationManager(val context: Context) {
    fun saveEmail(email: String) {
        context.getSharedPreferences(SH_TOKEN, MODE_PRIVATE).edit().putString(AUTH_TOKEN, email)
            .apply()
    }

    fun getEmail(): String {
        return context.getSharedPreferences(SH_TOKEN, MODE_PRIVATE).getString(AUTH_TOKEN, "") ?: ""
    }

    fun isAutorize(): Boolean {
        return (context.getSharedPreferences(SH_TOKEN, MODE_PRIVATE).getString(AUTH_TOKEN, "")
            ?: "") != ""
    }

    fun isAutorizeFlow(): Flow<Boolean> {
        val preferences = context.getSharedPreferences(SH_TOKEN, MODE_PRIVATE)
        val flow = MutableStateFlow(isAutorize())

        preferences.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == AUTH_TOKEN) {
                CoroutineScope(Dispatchers.IO).launch {
                    flow.emit((sharedPreferences.getString(AUTH_TOKEN, "") ?: "") != "")
                }
            }
        }
        return flow
    }

    companion object {
        private const val SH_TOKEN = "shAuth"
        private const val AUTH_TOKEN = "auth_token"
    }
}