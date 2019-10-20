package com.example.staysafesweetheart.viewmodel.settings.add.contact

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.contact.Contact
import com.example.staysafesweetheart.persistance.entities.contact.ContactValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddContactViewModel(
    private val repository: StaySafeRepository,
    private val contactValidator: ContactValidator
) : ViewModel() {

    companion object {
        private val TAG = AddContactViewModel::class.qualifiedName
    }

    val contactName = MutableLiveData<String>()

    val contactPhoneNumber = MutableLiveData<String>()

    val contactEmail = MutableLiveData<String>()

    private val _contactNameError = MutableLiveData<Boolean>()
    val contactNameError: LiveData<Boolean>
        get() = _contactNameError

    private val _contactPhoneNumberError = MutableLiveData<Boolean>()
    val contactPhoneNumberError: LiveData<Boolean>
        get() = _contactPhoneNumberError

    private val _contactEmailError = MutableLiveData<Boolean>()
    val contactEmailError: LiveData<Boolean>
        get() = _contactEmailError

    private val _contactSaved = MutableLiveData<Boolean>()
    val contactSaved: LiveData<Boolean>
        get() = _contactSaved

    fun onAddContact() {
        val name = contactName.value
        val phoneNumber = contactPhoneNumber.value
        val email = contactEmail.value

        val nameError = !contactValidator.validateName(name) || name == null
        val phoneNumberError =
            !contactValidator.validatePhoneNumber(phoneNumber) || phoneNumber == null
        val emailError = !contactValidator.validateEmail(email) || email == null

        _contactNameError.value = nameError
        _contactPhoneNumberError.value = phoneNumberError
        _contactEmailError.value = emailError

        if (!nameError && !phoneNumberError && !emailError) {
            viewModelScope.launch(Dispatchers.IO) {
                val newContact =
                    Contact(
                        null,
                        name!!,
                        email!!,
                        phoneNumber!!
                    )
                Log.i(TAG, "Inserting new contact {$newContact}")
                repository.insert(newContact)
                viewModelScope.launch(Dispatchers.Main) {
                    _contactSaved.value = true
                }
            }
        }
    }
}