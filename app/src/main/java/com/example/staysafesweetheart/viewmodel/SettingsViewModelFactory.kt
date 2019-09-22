package com.example.staysafesweetheart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.persistance.StaySafeRepository


class SettingsViewModelFactory(private val repository: StaySafeRepository) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}