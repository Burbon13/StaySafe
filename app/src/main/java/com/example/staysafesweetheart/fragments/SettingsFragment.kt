package com.example.staysafesweetheart.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.staysafesweetheart.R

import com.example.staysafesweetheart.adapters.ContactsListAdapter
import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.dagger2.StaySafeComponent
import com.example.staysafesweetheart.databinding.DialogFragmentSettingsAddContactBinding
import com.example.staysafesweetheart.databinding.FragmentSettingsBinding
import com.example.staysafesweetheart.viewmodel.NewContactDialogViewModel
import com.example.staysafesweetheart.viewmodel.NewContactDialogViewModelFactory
import com.example.staysafesweetheart.viewmodel.SettingsViewModel
import com.example.staysafesweetheart.viewmodel.SettingsViewModelFactory
import javax.inject.Inject


class SettingsFragment : Fragment() {

    @Inject
    lateinit var settingsViewModelFactory: SettingsViewModelFactory
    @Inject
    lateinit var contactsListAdapter: ContactsListAdapter
    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var daggerComponent: StaySafeComponent


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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        binding.settingsViewModel = settingsViewModel
        binding.recyclerViewContacts.adapter = contactsListAdapter
        binding.recyclerViewContacts.layoutManager = layoutManager

        return binding.root
    }

    // 1. Registration of View click listeners
    // 2. Subscription to observables (general observables, not necessarily Rx)
    // 3. Reflect the current state into UI (UI update)
    override fun onStart() {
        super.onStart()
        settingsViewModel.contacts.observe(this, Observer {
            contactsListAdapter.setContacts(it)
        })
        binding.buttonAddContact.setOnClickListener { onAddNewContact() }
    }

    // In this method you will unregister all observers and listeners and release all resources
    // that were allocated in onStart().
    override fun onStop() {
        super.onStop()
        settingsViewModel.contacts.removeObservers(this)
        binding.buttonAddContact.setOnClickListener(null)
    }


    private fun onAddNewContact() {
        Log.i("MEINE TAG", "1")
        // Creating the dialog window to add new contact
        val dialog = Dialog(this.context!!)

        val dialogBinding = DataBindingUtil.inflate<DialogFragmentSettingsAddContactBinding>(
            layoutInflater,
            R.layout.dialog_fragment_settings_add_contact,
            null,
            false
        )

        dialogBinding.viewModel =
            ViewModelProvider(this, daggerComponent.getNewContactDialogViewModelFactory()).get(
                NewContactDialogViewModel::class.java
            )

        dialog.setContentView(dialogBinding.root)

        dialog.show()
    }
}
