package com.example.staysafesweetheart.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.dagger2.DaggerStaySafeComponent
import com.example.staysafesweetheart.dagger2.SettingsModule
import com.example.staysafesweetheart.databinding.FragmentAddContactBinding
import com.example.staysafesweetheart.viewmodel.AddContactViewModel
import com.example.staysafesweetheart.viewmodel.AddContactViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AddContactFragment : Fragment() {
    @Inject
    lateinit var addContactViewModelFactory: AddContactViewModelFactory

    private lateinit var binding: FragmentAddContactBinding
    private lateinit var addContactViewModel: AddContactViewModel

    companion object {
        private val TAG = AddContactFragment::class.qualifiedName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerStaySafeComponent.builder().settingsModule(SettingsModule(context!!)).build()
            .inject(this)

        addContactViewModel =
            ViewModelProvider(this, addContactViewModelFactory).get(AddContactViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_contact,
            container,
            false
        )

        binding.addContactViewModel = addContactViewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        addContactViewModel.contactNameError.observe(this, Observer { error ->
            setEditTextError(
                binding.editTextAlertAddContactName,
                error,
                getString(R.string.invalid_name_error_message)
            )
        })
        addContactViewModel.contactPhoneNumberError.observe(this, Observer { error ->
            setEditTextError(
                binding.editTextAlertAddContactPhone,
                error,
                getString(R.string.invalid_phone_error_message)
            )
        })
        addContactViewModel.contactEmailError.observe(this, Observer { error ->
            setEditTextError(
                binding.editTextAlertAddContactEmail,
                error,
                getString(R.string.invalid_email_error_message)
            )
        })
        addContactViewModel.contactSaved.observe(this, Observer {
            findNavController().popBackStack()
        })
    }

    override fun onStop() {
        super.onStop()
        addContactViewModel.contactNameError.removeObservers(this)
        addContactViewModel.contactPhoneNumberError.removeObservers(this)
        addContactViewModel.contactEmailError.removeObservers(this)
        addContactViewModel.contactSaved.removeObservers(this)
    }

    private fun setEditTextError(editText: EditText, error: Boolean, text: String) {
        if (error) {
            editText.error = text
        } else {
            editText.error = null
        }
    }
}
