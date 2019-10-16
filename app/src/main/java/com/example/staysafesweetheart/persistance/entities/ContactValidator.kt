package com.example.staysafesweetheart.persistance.entities

import android.text.TextUtils
import android.util.Log
import android.util.Patterns


/**
 * Class which contains validation functions for a Contact fields (email, phone & name)
 */
class ContactValidator {
    companion object {
        private val TAG = ContactValidator::class.qualifiedName
    }

    /**
     *
     */
    fun validateEmail(email: String?): Boolean {
        Log.d(TAG, "Verifying email $email")
        return email != null && !TextUtils.isEmpty(email)
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePhoneNumber(phone: String?): Boolean {
        Log.d(TAG, "Verifying phone $phone")
        return phone != null && !TextUtils.isEmpty(phone) &&
                Patterns.PHONE.matcher(phone).matches()
    }

    fun validateName(name: String?): Boolean {
        Log.d(TAG, "Verifying name $name")
        return name != null && !TextUtils.isEmpty(name)
    }
}