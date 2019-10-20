package com.example.staysafesweetheart.viewmodel.settings.my.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.contact.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyContactsViewModel(private val repository: StaySafeRepository) : ViewModel() {

    val contacts = repository.allContacts

    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(contact)
    }
}