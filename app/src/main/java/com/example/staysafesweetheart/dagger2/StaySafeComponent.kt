package com.example.staysafesweetheart.dagger2

import com.example.staysafesweetheart.fragments.MyContactsFragment
import com.example.staysafesweetheart.fragments.SettingsFragment
import dagger.Component


@Component(modules = [SettingsModule::class])
interface StaySafeComponent {
    fun inject(settingsFragment: SettingsFragment)
    fun inject(myContactsFragment: MyContactsFragment)
}