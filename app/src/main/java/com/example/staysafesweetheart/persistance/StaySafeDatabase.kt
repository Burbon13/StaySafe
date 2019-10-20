package com.example.staysafesweetheart.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.persistance.daos.TemplateMessageDao
import com.example.staysafesweetheart.persistance.entities.contact.Contact
import com.example.staysafesweetheart.persistance.entities.template.message.TemplateMessage


@Database(entities = [Contact::class, TemplateMessage::class], version = 1, exportSchema = false)
abstract class StaySafeDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
    abstract fun templateMessageDao(): TemplateMessageDao
}
