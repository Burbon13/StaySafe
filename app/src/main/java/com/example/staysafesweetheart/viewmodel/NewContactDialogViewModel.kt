package com.example.staysafesweetheart.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.staysafesweetheart.R
import com.example.staysafesweetheart.persistance.daos.ContactDao
import com.example.staysafesweetheart.persistance.entities.Contact
import com.example.staysafesweetheart.persistance.entities.ContactValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * ViewModel for the Dialog in which the user will introduce contact information about a new
 * emergency contact [Contact]
 */
class NewContactDialogViewModel(
    private val contactDao: ContactDao,
    private val contactValidator: ContactValidator
) : ViewModel() {

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = Job()

    /**
     * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [viewModelJob], any coroutine started in this scope can be cancelled
     * by calling `viewModelJob.cancel()`
     *
     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android. This is a sensible default because most coroutines started by
     * a [ViewModel] update the UI after performing some processing.
     */
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Will contain the new contact's data
    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact>
        get() = _contact

    private val _contactEmailError = MutableLiveData<Boolean>()
    val contactEmailError: LiveData<Boolean>
        get() = _contactEmailError

    private val _contactPhoneError = MutableLiveData<Boolean>()
    val contactPhoneError: LiveData<Boolean>
        get() = _contactPhoneError

    private val _contactNameError = MutableLiveData<Boolean>()
    val contactNameError: LiveData<Boolean>
        get() = _contactNameError

    private val _verifyingNewContact = MutableLiveData<Boolean?>()
    val verifyingNewContact: LiveData<Boolean?>
        get() = _verifyingNewContact

    private val _contactAdded = MutableLiveData<Boolean?>()
    val contactAdded: LiveData<Boolean?>
        get() = _contactAdded

    fun addNewContact() {
        // Settings this to true will alert the observer that the application is verifying if
        // the contact's data is valid
        _verifyingNewContact.value = true

        val contactValue = contact.value

        // Settings this to true will alert the observer of the error regarding the name
        _contactNameError.value = contactValidator.validateName(contactValue?.name)

        // Settings this to true will alert the observer of the error regarding the email
        _contactEmailError.value = contactValidator.validateEmail(contactValue?.email)

        // Settings this to true will alert the observer of the error regarding the name
        _verifyingNewContact.value = contactValidator.validateName(contactValue?.name)

        // Settings this to true will alert the observer that the contact verification has finished
        _verifyingNewContact.value = true

        if (!_contactNameError.value!! && !_contactEmailError.value!!
            && !_verifyingNewContact.value!!
        ) {
            insertNewContact(contactValue!!)
        } else {
            // Setting this to false will alert the observer that there was something wrong with the
            // contact's data
            _contactAdded.value = false

        }
    }

    private fun insertNewContact(contact: Contact) {
        uiScope.launch {
            // IO is a thread pool for running operations that access the disk, such as
            // our Room database.
            withContext(Dispatchers.IO) {
                contactDao.insertContact(contact)
            }
            // Setting this state variable to true will alert the observer that the new contact
            // was successfully added
            _contactAdded.value = true
        }
    }
}