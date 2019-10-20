package com.example.staysafesweetheart.fragments.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.staysafesweetheart.R

import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.dagger2.StaySafeComponent
import com.example.staysafesweetheart.databinding.FragmentSettingsBinding
import com.example.staysafesweetheart.viewmodel.settings.SettingsViewModel
import com.example.staysafesweetheart.viewmodel.settings.SettingsViewModelFactory
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
        Log.d(TAG, "onCreate()")
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
        Log.d(TAG, "onCreateView()")

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
        Log.d(TAG, "onStart()")
        binding.emergencyContactsButton.setOnClickListener { openMyEmergencyContactsFragment() }
        binding.templateMessagesButton.setOnClickListener { openTemplateMessagesFragment() }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
        binding.emergencyContactsButton.setOnClickListener(null)
        binding.templateMessagesButton.setOnClickListener(null)
    }

    private fun openMyEmergencyContactsFragment() {
        Log.d(TAG, "Opening 'MyEmergencyContacts' fragment")
        findNavController().navigate(R.id.action_settingsFragment_to_myContactsFragment)
    }

    private fun openTemplateMessagesFragment() {
        Log.d(TAG, "Opening 'TemplateMessages' fragment")
    }
}
