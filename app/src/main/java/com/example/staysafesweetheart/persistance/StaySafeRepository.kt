package com.example.staysafesweetheart.persistance

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.persistance.daos.TemplateMessageDao
import com.example.staysafesweetheart.persistance.entities.contact.Contact
import com.example.staysafesweetheart.persistance.entities.template.message.TemplateMessage


class StaySafeRepository(
    private val contactDao: ContactDao,
    private val templateMessageDao: TemplateMessageDao
) {
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    @WorkerThread
    suspend fun insert(contact: Contact) {
        contactDao.insertContact(contact)
    }

    @WorkerThread
    suspend fun insertTemplateMessage(templateMessage: TemplateMessage) {
        templateMessageDao.insertTemplateMessage(templateMessage)
    }
}