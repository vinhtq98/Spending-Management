package com.jrsoftware.template.ui.base.ext

import android.content.ClipData
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.SystemClock
import android.widget.Toast
import com.google.gson.Gson

internal const val CHECK_TIME_MULTI_CLICK = 500
private var mLastClickTime: Long = 0

fun Context.canTouch(): Boolean {
    if (SystemClock.elapsedRealtime() - mLastClickTime < CHECK_TIME_MULTI_CLICK) {
        return false
    }
    mLastClickTime = SystemClock.elapsedRealtime()
    return true
}

fun Context.showToastByString(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToastById(id: Int) {
    Toast.makeText(this, resources.getString(id), Toast.LENGTH_SHORT).show()
}

fun Context.getStringById(id: Int): String {
    return resources.getString(id)
}

fun Context.getCurrentSdkVersion(): Int {
    return Build.VERSION.SDK_INT
}

fun Context.isNetwork(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null && cm.activeNetworkInfo?.isConnected == true
}

fun Context.copyToClipboard(text: CharSequence){
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label",text)
    clipboard.setPrimaryClip(clip)
}

fun Context.paste() : String{
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    var pasteData = ""
    // If it does contain data, decide if you can handle the data.
    if (!(clipboard!!.hasPrimaryClip())) {
    } else if (!(clipboard.primaryClipDescription!!.hasMimeType(MIMETYPE_TEXT_PLAIN))) {
        // since the clipboard has data but it is not plain text
    } else {
        //since the clipboard contains plain text.

        val item = clipboard.primaryClip?.getItemAt(0)

        // Gets the clipboard as text.
        pasteData = item?.text.toString()
    }
    return pasteData
}

fun <T> Context.convertObjectToJson(obj: T): String = Gson().toJson(obj)

fun <T> Context.convertJsonToObject(json: String, classOfT: Class<T>): T? =
    Gson().fromJson(json, classOfT)