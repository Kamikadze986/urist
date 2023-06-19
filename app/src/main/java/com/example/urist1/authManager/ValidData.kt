package com.example.urist1.authManager

import android.content.Context
import java.util.regex.Pattern

class ValidData(val context: Context) {
    private val pattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,5}$", Pattern.CASE_INSENSITIVE)

    fun isValidEmail(email: String): Boolean {
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}