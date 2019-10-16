package com.example.staysafesweetheart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.ContactValidator


class NewContactDialogViewModelFactory(
    private val repository: StaySafeRepository,
    private val contactValidator: ContactValidator
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewContactDialogViewModel::class.java)) {
            return NewContactDialogViewModel(repository, contactValidator) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}