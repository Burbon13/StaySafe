package com.example.staysafesweetheart.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.staysafesweetheart.persistance.entities.contact.Contact


@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Query("SELECT * FROM contacts ORDER BY position ASC")
    fun getAllContacts(): LiveData<List<Contact>>
}