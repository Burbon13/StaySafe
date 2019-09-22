package com.example.staysafesweetheart.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.persistance.entities.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class StaySafeDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        private const val DATABASE_NAME = "stay_safe_database_0.2"
        @Volatile
        private var INSTANCE: StaySafeDatabase? = null

        fun getDatabase(context: Context): StaySafeDatabase {
            val currentInstance = INSTANCE
            if (currentInstance != null) {
                return currentInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StaySafeDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
