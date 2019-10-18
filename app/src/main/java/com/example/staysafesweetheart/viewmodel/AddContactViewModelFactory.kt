package com.example.staysafesweetheart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddContactViewModelFactory : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            return MainActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
