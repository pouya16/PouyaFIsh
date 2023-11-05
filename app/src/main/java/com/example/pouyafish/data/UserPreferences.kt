package com.example.pouyafish.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(private val context: Context) {
    private val PREFS_NAME = "UserPreferences"
    private val MOBILE_NUMBER_KEY = "mobileNumber"

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveMobileNumber(mobileNumber: String) {
        val editor = sharedPreferences.edit()
        editor.putString(MOBILE_NUMBER_KEY, mobileNumber)
        editor.apply()
    }

    fun getMobileNumber(): String {
        return sharedPreferences.getString(MOBILE_NUMBER_KEY, "") ?: ""
    }

    fun clearMobileNumber() {
        val editor = sharedPreferences.edit()
        editor.remove(MOBILE_NUMBER_KEY)
        editor.apply()
    }
}
