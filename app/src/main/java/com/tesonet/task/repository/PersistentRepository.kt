package com.tesonet.task.repository

import android.content.SharedPreferences
import com.tesonet.task.di.TOKEN_DEFAULT_VALUE
import com.tesonet.task.di.TOKEN_VALUE_KEY

class PersistentRepository(private val sharedPreferences: SharedPreferences) {

    fun saveTokenToPersistent(token: String) {
        sharedPreferences.edit().putString(TOKEN_VALUE_KEY, token).apply()
    }

    fun getTokenFromPersistent(): String? {
        return sharedPreferences.getString(TOKEN_VALUE_KEY, TOKEN_DEFAULT_VALUE)
    }
}