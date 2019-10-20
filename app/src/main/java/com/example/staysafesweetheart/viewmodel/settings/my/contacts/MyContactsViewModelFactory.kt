package com.example.staysafesweetheart.viewmodel.settings.my.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.persistance.StaySafeRepository


class MyContactsViewModelFactory(private val repository: StaySafeRepository) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyContactsViewModel::class.java)) {
            return MyContactsViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}