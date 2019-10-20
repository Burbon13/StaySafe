package com.example.staysafesweetheart.persistance.entities.template.message

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "template_messages")
data class TemplateMessage(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val text: String,
    val dateCreated: String
)