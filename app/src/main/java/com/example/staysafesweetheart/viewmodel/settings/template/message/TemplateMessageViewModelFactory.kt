package com.example.staysafesweetheart.viewmodel.settings.template.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.staysafesweetheart.persistance.StaySafeRepository
import com.example.staysafesweetheart.persistance.entities.template.message.TemplateMessageValidator


class TemplateMessageViewModelFactory(
    private val repository: StaySafeRepository,
    private val templateMessageValidator: TemplateMessageValidator
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TemplateMessageViewModel::class.java)) {
            return TemplateMessageViewModel(repository, templateMessageValidator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}