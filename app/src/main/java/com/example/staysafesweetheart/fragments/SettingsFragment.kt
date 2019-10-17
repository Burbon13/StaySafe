package com.example.staysafesweetheart.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.R

import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.dagger2.StaySafeComponent
import com.example.staysafesweetheart.databinding.FragmentSettingsBinding
import com.example.staysafesweetheart.viewmodel.SettingsViewModel
import com.example.staysafesweetheart.viewmodel.SettingsViewModelFactory
import javax.inject.Inject


class SettingsFragment : Fragment() {

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var daggerComponent: StaySafeComponent

    companion object {
        private val TAG = SettingsFragment::class.qualifiedName
    }

    //Dependency injection and initialization of Fragment’s members takes place here.
    //You must not touch or do anything related to Android Views in Fragment’s onCreate(Bundle)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        daggerComponent =
            DaggerStaySafeComponent.builder().settingsModule(SettingsModule(context!!)).build()

        daggerComponent.inject(this)
        settingsViewModel =
            ViewModelProvider(this, settingsViewModelFactory).get(SettingsViewModel::class.java)
    }

    // All Fragment’s members holding references to objects related to View hierarchy must be
    // assigned inside View onCreateView.
    // In other words, if Fragment holds references to Views or related objects in it’s fields,
    // make sure that all these fields are assigned in this method. This is really important.
    // Every Fragment’s field related to View hierarchy must be assigned in this method.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings,
            container,
            false
        )

        binding.settingsViewModel = settingsViewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "FUCK")
        settingsViewModel.yourEmergencyContactsPressed.observe(this, Observer {
            openMyEmergencyContactsFragment()
        })
        settingsViewModel.templateMessagesPressed.observe(this, Observer {
            openTemplateMessagesFragment()
        })
    }

    override fun onStop() {
        super.onStop()
        settingsViewModel.yourEmergencyContactsPressed.removeObservers(this)
        settingsViewModel.templateMessagesPressed.removeObservers(this)
    }

    private fun openMyEmergencyContactsFragment() {
        Log.d(TAG, "Opening MyEmergencyContacts fragment")
    }

    private fun openTemplateMessagesFragment() {
        Log.d(TAG, "Opening TemplateMessages fragment")
    }
}
