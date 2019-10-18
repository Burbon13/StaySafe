package com.example.staysafesweetheart.dagger2

import com.example.staysafesweetheart.viewmodel.MainActivityViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {
    @Provides
    fun provideMainActivityViewModelFactory(): MainActivityViewModelFactory {
        return MainActivityViewModelFactory()
    }
}