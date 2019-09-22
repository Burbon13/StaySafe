package com.example.staysafesweetheart.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.persistance.entities.Contact


@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class StaySafeDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
