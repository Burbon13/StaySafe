package com.example.staysafesweetheart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 *
 */
class SettingsViewModel : ViewModel() {

    /**
     * Changes value when the "Your emergency contacts" button is pressed
     */
    private val _yourEmergencyContactsPressed = MutableLiveData<Boolean>()
    val yourEmergencyContactsPressed: LiveData<Boolean>
        get() = _yourEmergencyContactsPressed

    /**
     * Changes value when the "Template messages" button is pressed
     */
    private val _templateMessagesPressed = MutableLiveData<Boolean>()
    val templateMessagesPressed: LiveData<Boolean>
        get() = _templateMessagesPressed

    /**
     * Called when the "Your emergency contacts" button is pressed
     */
    fun yourEmergencyContactsOnPressed() {
        _yourEmergencyContactsPressed.value = true
    }

    /**
     * Called when the "Template messages" button is pressed
     */
    fun templateMessagesPressedOnPressed() {
        _templateMessagesPressed.value = true
    }
}