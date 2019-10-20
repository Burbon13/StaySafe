package com.example.staysafesweetheart.viewmodel.settings.add.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.contact.ContactValidator


class AddContactViewModelFactory(
    private val repository: StaySafeRepository,
    private val contactValidator: ContactValidator
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            return AddContactViewModel(
                repository,
                contactValidator
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
