package com.example.staysafesweetheart.viewmodel.settings.template.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.template.message.TemplateMessage
import com.example.staysafesweetheart.persistance.entities.template.message.TemplateMessageValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar


class TemplateMessageViewModel(
    private val repository: StaySafeRepository,
    private val templateMessageValidator: TemplateMessageValidator
) : ViewModel() {

    val newTemplateMessageText = MutableLiveData<String>()

    private val _templateMessageTextError = MutableLiveData<Boolean>()
    val templateMessageTextError: LiveData<Boolean>
        get() = _templateMessageTextError

    private val _templateMessageTextSaved = MutableLiveData<Boolean>()
    val templateMessageTextSaved: LiveData<Boolean>
        get() = _templateMessageTextSaved

    val templateMessage = repository.templateMessage

    fun saveNewTemplateMessage() {
        val text = newTemplateMessageText.value
        val textError = text == null || !templateMessageValidator.validateText(text)

        _templateMessageTextError.value = textError

        if (!textError) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateTemplateMessage(
                    TemplateMessage(
                        null,
                        text!!,
                        Calendar.getInstance().time.toString()
                    )
                )
                viewModelScope.launch(Dispatchers.Main) {
                    _templateMessageTextSaved.value = true
                }
            }
        } else {
            _templateMessageTextSaved.value = false
        }
    }
}