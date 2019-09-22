package com.example.staysafesweetheart.dagger2

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.staysafesweetheart.adapters.ContactsListAdapter
import com.example.staysafesweetheart.persistance.StaySafeDatabase
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.viewmodel.SettingsViewModel
import com.example.staysafesweetheart.viewmodel.SettingsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SettingsModule(private val context: Context) {

    companion object {
        private const val DATABASE_NAME = "stay_safe_database_0.2"
    }

    @Provides
    fun provideContactsListAdapter(): ContactsListAdapter {
        return ContactsListAdapter(context)
    }

    @Provides
    fun provideLinearLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    fun provideStaySafeDatabase(): StaySafeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            StaySafeDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideContactDao(staySafeDatabase: StaySafeDatabase): ContactDao {
        return staySafeDatabase.contactDao()
    }

    @Provides
    fun provideStaySafeRepository(contactDao: ContactDao): StaySafeRepository {
        return StaySafeRepository(contactDao)
    }

    @Provides
    fun provideSettingsViewModelFactory(repository: StaySafeRepository): SettingsViewModelFactory {
        return SettingsViewModelFactory(repository)
    }
}