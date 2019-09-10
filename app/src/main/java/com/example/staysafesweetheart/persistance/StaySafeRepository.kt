package com.example.staysafesweetheart.persistance

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.persistance.entities.Contact

class StaySafeRepository(private val contactDao: ContactDao) {
    val TAG = StaySafeRepository::class.java.name

    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    @WorkerThread
    suspend fun insert(contact: Contact) {
        Log.i(TAG, "Adding contact {$contact}")
        contactDao.insertContact(contact)
    }
}