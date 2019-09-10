package com.example.staysafesweetheart.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.staysafesweetheart.persistance.StaySafeDatabase
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = SettingsViewModel::class.java.name

    private val repository: StaySafeRepository
    val contacts: LiveData<List<Contact>>

    init {
        Log.i(TAG, "Initializing SettingsViewModel")
        repository =
            StaySafeRepository(StaySafeDatabase.getDatabase(application.applicationContext).contactDao())
        Log.i(TAG, "Set up repository {$repository}")
        contacts = repository.allContacts
        Log.i(TAG, "Loaded contacts {${contacts.value}}")
    }

    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        Log.i(TAG, "Inserting new contact {$contact}")
        repository.insert(contact)
    }
}