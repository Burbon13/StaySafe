package com.example.staysafesweetheart.persistance.entities

import android.text.TextUtils
import android.util.Patterns

class ContactValidator {

    fun validateEmail(email: String?): Boolean {
        return email != null && !TextUtils.isEmpty(email)
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePhoneNumber(phone: String?): Boolean {
        return phone != null && !TextUtils.isEmpty(phone) &&
                Patterns.PHONE.matcher(phone).matches()
    }

    fun validateName(name: String?): Boolean {
        return name != null && !TextUtils.isEmpty(name)
    }
}