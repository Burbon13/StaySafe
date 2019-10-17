package com.example.staysafesweetheart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MyContactsViewModelFactory : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyContactsViewModel::class.java)) {
            return MyContactsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}