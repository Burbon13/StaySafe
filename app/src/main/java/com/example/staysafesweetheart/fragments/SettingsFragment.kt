package com.example.staysafesweetheart.fragments


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.staysafesweetheart.R

import com.example.staysafesweetheart.adapters.ContactsListAdapter
import com.example.staysafesweetheart.databinding.FragmentSettingsBinding
import com.example.staysafesweetheart.persistance.entities.Contact
import com.example.staysafesweetheart.persistance.entities.ContactValidator
import com.example.staysafesweetheart.viewmodel.SettingsViewModel


class SettingsFragment : Fragment() {
    private val TAG = SettingsFragment::class.java.name

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        binding.settingsViewModel = settingsViewModel

        val adapter = ContactsListAdapter(this.context!!)

        settingsViewModel.contacts.observe(this, Observer {
            adapter.setContacts(it)
        })

        binding.recyclerViewContacts.adapter = adapter
        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(this.context!!)
        binding.buttonAddContact.setOnClickListener { onAddNewContact() }

        return binding.root
    }

    private fun onAddNewContact() {
        Log.i(TAG, "Create new contact button pressed")

        val dialog = Dialog(this.context!!)
        dialog.setContentView(R.layout.dialog_fragment_settings_add_contact)
        (dialog.findViewById(R.id.button_add_new_contact) as Button).setOnClickListener {
            Log.i(TAG, "Insert new contact")

            val nameEditText =
                dialog.findViewById(R.id.edit_text_alert_add_contact_name) as EditText
            val emailEditText =
                dialog.findViewById(R.id.edit_text_alert_add_contact_email) as EditText
            val phoneEditText =
                dialog.findViewById(R.id.edit_text_alert_add_contact_phone) as EditText

            var valid = true

            if (!ContactValidator.validateName(nameEditText.text.toString())) {
                nameEditText.error = "Cannot be empty"
                valid = false
            }
            if (!ContactValidator.validateEmailTextField(emailEditText.text.toString())) {
                emailEditText.error = "Invalid email address"
                valid = false
            }
            if (!ContactValidator.validatePhoneNumber(phoneEditText.text.toString())) {
                phoneEditText.error = "Invalid phone number"
                valid = false
            }

            if (valid) {
                binding.settingsViewModel!!.insert(
                    Contact(
                        null,
                        nameEditText.text.toString(),
                        emailEditText.text.toString(),
                        phoneEditText.text.toString()
                    )
                )
                dialog.dismiss()
            }

        }
        dialog.show()
    }
}
