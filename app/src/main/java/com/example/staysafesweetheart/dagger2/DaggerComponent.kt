package com.example.staysafesweetheart.dagger2

import com.example.staysafesweetheart.fragments.SettingsFragment
import com.example.staysafesweetheart.viewmodel.NewContactDialogViewModelFactory
import dagger.Component


@Component(modules = [SettingsModule::class, NewContactDialogModule::class])
interface StaySafeComponent {
    fun inject(settingsFragment: SettingsFragment)
    fun getNewContactDialogViewModelFactory(): NewContactDialogViewModelFactory
}