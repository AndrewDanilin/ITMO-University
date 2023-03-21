package com.andrewdanilin.homework7.extensions

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.andrewdanilin.homework7.ChatActivity
import com.andrewdanilin.homework7.MainActivity
import com.andrewdanilin.homework7.utils.Utils
import org.json.JSONArray
import org.json.JSONObject

fun MainActivity.permissionIsGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this,
        permission) == PackageManager.PERMISSION_GRANTED
}

fun MainActivity.requestPermission(permission: String) {
    ActivityCompat.requestPermissions(this,
        arrayOf(permission),
        Utils.REQUEST_PERMISSION_ID
    )
}

fun ChatActivity.permissionIsGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this,
        permission) == PackageManager.PERMISSION_GRANTED
}

fun ChatActivity.requestPermission(permission: String) {
    ActivityCompat.requestPermissions(this,
        arrayOf(permission),
        Utils.REQUEST_PERMISSION_ID
    )
}

operator fun JSONArray.iterator(): Iterator<JSONObject> =
    (0 until this.length()).asSequence().map { this.get(it) as JSONObject }.iterator()