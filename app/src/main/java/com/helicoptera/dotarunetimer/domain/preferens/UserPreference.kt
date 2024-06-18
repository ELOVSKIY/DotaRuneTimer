package com.helicoptera.dotarunetimer.domain.preferens

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject


class UserPreference @Inject constructor(application: Application) {

    var shared: SharedPreferences = application.getSharedPreferences(USER_PREFERENCES_KEY, MODE_PRIVATE)

    fun isEnabled(key: String): Boolean {
        return shared.getBoolean(key, true)
    }

    fun setEnabled(key: String, enabled: Boolean) {
        return shared.edit {
            putBoolean(key, enabled)
        }
    }

    private companion object {
        private const val USER_PREFERENCES_KEY = "USER_PREFERENCES"
    }
}