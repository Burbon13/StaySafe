package com.example.staysafesweetheart.persistance.entities

import android.text.TextUtils

class ContactValidator {
    companion object {
        fun validateEmailTextField(email: String): Boolean {
            return !TextUtils.isEmpty(email)
                    && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun validatePhoneNumber(phone: String): Boolean {
            return !TextUtils.isEmpty(phone) &&
                    android.util.Patterns.PHONE.matcher(phone).matches()
        }

        fun validateName(name: String): Boolean {
            return !TextUtils.isEmpty(name)
        }
    }
}