package com.example.data_local.utils

import android.util.Base64

object Base64tHelper {
    fun fromBase64(it: String): String? {
        return try {
            String(Base64.decode(it, Base64.NO_WRAP))
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    fun toBase64(data: String) = try {
        Base64.encodeToString(data.toByteArray(), Base64.NO_WRAP)
    } catch (e: AssertionError) {
        null
    }

}