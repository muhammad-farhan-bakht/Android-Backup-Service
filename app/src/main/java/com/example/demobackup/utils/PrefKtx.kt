package com.example.demobackup.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.demobackup.utils.Constant.PREFS_FILENAME

object PrefKtx {

    private var sharedPrefs: SharedPreferences? = null

    fun initializePref(context: Context) {
        sharedPrefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }

    fun getPref() : SharedPreferences? {
        return sharedPrefs
    }

    /**
     * Extension [SharedPreferences]: Put all Most all Primitive Data type in pref
     * @param key Key of th the Value
     * @param value the value which will saved in this pref
     */
    fun <T> SharedPreferences.putAnyThing(key: String, value: T) {
        with(edit()) {
            when (value) {
                is Boolean -> putBoolean(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is Float -> putFloat(key, value)
                is String -> putString(key, value)
                else -> throw IllegalArgumentException()
            }.apply()
        }
    }

    /**
     * Extension [SharedPreferences]: Get all Most all Primitive Data type from pref
     * @param key Key of th the Value
     * @param defaultValue the value which will replace to the original value if original Value not found
     */
    fun <T> SharedPreferences.getAnyThing(key: String, defaultValue: T): T {
        with(this) {
            val result: Any = when (defaultValue) {
                is String -> getString(key, defaultValue) as String
                is Boolean -> getBoolean(key, defaultValue)
                is Int -> getInt(key, defaultValue)
                is Long -> getLong(key, defaultValue)
                is Float -> getFloat(key, defaultValue)
                else -> throw IllegalArgumentException()
            }
            @Suppress("UNCHECKED_CAST")
            return result as T
        }
    }

}