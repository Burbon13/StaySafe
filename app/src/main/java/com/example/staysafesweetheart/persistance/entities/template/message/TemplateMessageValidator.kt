package com.example.staysafesweetheart.persistance.entities.template.message


class TemplateMessageValidator {

    companion object {
        private const val ZERO_LENGTH = 0
        private const val MAX_LENGTH_TEXT = 500
    }

    fun validateText(text: String?): Boolean {
        return text != null && text.length > ZERO_LENGTH && text.length < MAX_LENGTH_TEXT
    }
}