package com.example.staysafesweetheart.dagger2

import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.ContactValidator
import com.example.staysafesweetheart.viewmodel.NewContactDialogViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class NewContactDialogModule {
    @Provides
    fun provideContactValidator(): ContactValidator {
        return ContactValidator()
    }

    @Provides
    fun provideNewContactDialogViewModelFactory(
        repository: StaySafeRepository,
        contactValidator: ContactValidator
    ): NewContactDialogViewModelFactory {
        return NewContactDialogViewModelFactory(repository, contactValidator)
    }
}