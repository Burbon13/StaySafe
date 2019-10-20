package com.example.staysafesweetheart.dagger2

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.staysafesweetheart.adapters.ContactsListAdapter
import com.example.staysafesweetheart.persistance.StaySafeDatabase
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.persistance.daos.TemplateMessageDao
import com.example.staysafesweetheart.persistance.entities.contact.ContactValidator
import com.example.staysafesweetheart.viewmodel.settings.add.contact.AddContactViewModelFactory
import com.example.staysafesweetheart.viewmodel.settings.my.contacts.MyContactsViewModelFactory
import com.example.staysafesweetheart.viewmodel.settings.SettingsViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class SettingsModule(private val context: Context) {

    companion object {
        private const val DATABASE_NAME = "stay_safe_database_0.4"
    }

    @Provides
    fun provideContactValidator(): ContactValidator {
        return ContactValidator()
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
    fun provideTemplateMessageDao(staySafeDatabase: StaySafeDatabase): TemplateMessageDao {
        return staySafeDatabase.templateMessageDao()
    }

    @Provides
    fun provideStaySafeRepository(
        contactDao: ContactDao,
        templateMessageDao: TemplateMessageDao
    ): StaySafeRepository {
        return StaySafeRepository(contactDao, templateMessageDao)
    }

    @Provides
    fun provideSettingsViewModelFactory(): SettingsViewModelFactory {
        return SettingsViewModelFactory()
    }

    @Provides
    fun provideMyContactsViewModelFactory(repository: StaySafeRepository): MyContactsViewModelFactory {
        return MyContactsViewModelFactory(
            repository
        )
    }

    @Provides
    fun provideAddContactViewModelFactory(
        repository: StaySafeRepository,
        contactValidator: ContactValidator
    ): AddContactViewModelFactory {
        return AddContactViewModelFactory(
            repository,
            contactValidator
        )
    }
}