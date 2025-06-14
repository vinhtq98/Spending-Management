package com.jrsoftware.template.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharePreferenceUtil(val context: Context) {

    companion object {
        val APP_SHARE_KEY = "com.itg.template"

        @SuppressLint("StaticFieldLeak")
        private var instance: SharePreferenceUtil? = null

        fun initializeInstance(context: Context) {
            if (instance == null) {
                instance = SharePreferenceUtil(context)
            }
        }

        fun getInstance(): SharePreferenceUtil {
            if (instance == null) {
                throw IllegalStateException(
                    SharePreferenceUtil::class.java.simpleName + " is not initialized, call initializeInstance(..) method first."
                )
            }
            return instance as SharePreferenceUtil
        }
    }


    fun getSharedPreferences(): SharedPreferences? {
        return context.getSharedPreferences(APP_SHARE_KEY, Context.MODE_PRIVATE)
    }


    val mPref: SharedPreferences = context.getSharedPreferences(APP_SHARE_KEY, Context.MODE_PRIVATE)

    fun setStringValue(key: String, value: String?) {
        mPref.edit().putString(key, value).commit()
    }

    fun getStringValue(key: String, default: String?): String? {
        return mPref.getString(key, default)
    }

    fun getStringValue(key: String): String? {
        return mPref.getString(key, "")
    }

    fun setIntValue(key: String, value: Int) {
        mPref.edit().putInt(key, value).apply()
    }

    fun getIntValue(key: String, defaultValue: Int): Int {
        return mPref.getInt(key, defaultValue)
    }

    fun setFloatValue(key: String, value: Float) {
        mPref.edit().putFloat(key, value).apply()
    }

    fun getFloatValue(key: String, i: Float): Float {
        return mPref.getFloat(key, i)
    }

    fun setLongValue(key: String, value: Long) {
        mPref.edit().putLong(key, value).apply()
    }

    fun getLongValue(key: String, i: Long): Long {
        return mPref.getLong(key, i)
    }

    fun setBooleanValue(key: String, value: Boolean) {
        mPref.edit().putBoolean(key, value).commit()
    }

    fun getBooleanValue(key: String): Boolean {
        return mPref.getBoolean(key, false)
    }

    fun getBooleanValue(key: String, default: Boolean): Boolean {
        return mPref.getBoolean(key, default)
    }

    fun remove(key: String) {
        mPref.edit().remove(key).commit()
    }

    fun clear(): Boolean {
        return mPref.edit().clear().commit()
    }

    inline fun <reified T> saveListToSharePreference(key: String, list: MutableList<T>) {
        val gson = Gson()
        val json = gson.toJson(list)
        mPref.edit().putString(key, json).apply()
    }

    inline fun <reified T> getListFromSharePreference(key: String): MutableList<T> {
        var arrayItems = mutableListOf<T>()
        val serializedObject: String? = mPref.getString(key, null)
        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<MutableList<T?>?>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
        }
        return arrayItems
    }

    inline fun <reified T> saveObjectToSharePreference(key: String, data: T) {
        val gson = Gson()
        val json = gson.toJson(data)
        mPref.edit().putString(key, json).apply()
    }

    inline fun <reified T> getObjectFromSharePreference(key: String): T? {
        val serializedObject: String? = mPref.getString(key, null)
        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<T?>() {}.type
            return gson.fromJson(serializedObject, type)
        }
        return null
    }


    fun setValue(keyName: String, value: Any?) {
        val editor = mPref.edit()
        when (value) {
            is Int -> editor?.putInt(keyName, value)
            is Float -> editor?.putFloat(keyName, value)
            is Long -> editor?.putLong(keyName, value)
            is Boolean -> editor?.putBoolean(keyName, value)
            is String -> editor?.putString(keyName, value)
        }
        editor?.apply()
    }


    fun <T> getValue(keyName: String, defaultValue: T): T = when (defaultValue) {
        is Int -> (mPref.getInt(keyName, defaultValue) ?: defaultValue) as T
        is Long -> (mPref.getLong(keyName, defaultValue) ?: defaultValue) as T
        is Float -> (mPref.getFloat(keyName, defaultValue) ?: defaultValue) as T
        is Boolean -> (mPref.getBoolean(keyName, defaultValue) ?: defaultValue) as T
        is String -> (mPref.getString(keyName, defaultValue) ?: defaultValue) as T
        else -> defaultValue
    }

    var jsonLanguageLeft: String
        get() = getValue("language_left", "{}")
        set(value) = setValue("language_left", value)

    var jsonLanguageRight: String
        get() = getValue("language_right", "{}")
        set(value) = setValue("language_right", value)

    var countCameraPermission: Int
        get() = getValue("count_camera_permission", 0)
        set(value) = setValue("count_camera_permission", value)
}