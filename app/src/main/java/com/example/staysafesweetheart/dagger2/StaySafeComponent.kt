package com.example.staysafesweetheart.dagger2

import com.example.staysafesweetheart.activities.MainActivity
import com.example.staysafesweetheart.fragments.settings.AddContactFragment
import com.example.staysafesweetheart.fragments.settings.MyContactsFragment
import com.example.staysafesweetheart.fragments.settings.SettingsFragment
import dagger.Component


@Component(modules = [MainActivityModule::class, SettingsModule::class])
interface StaySafeComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(myContactsFragment: MyContactsFragment)
    fun inject(addContactFragment: AddContactFragment)
}