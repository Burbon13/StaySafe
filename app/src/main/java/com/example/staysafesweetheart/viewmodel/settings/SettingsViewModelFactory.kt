package com.example.staysafesweetheart.viewmodel.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SettingsViewModelFactory : ViewModelProvider.Factory {

    init {
        Log.d("FUCK", "FUCK FUCK 32425")
    }

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            Log.d("FUCK", "FUCK FUCK FUCK")
            return SettingsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
