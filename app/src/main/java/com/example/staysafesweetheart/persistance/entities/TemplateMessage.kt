package com.example.staysafesweetheart.persistance.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "template_messages")
data class TemplateMessage(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val text: String,
    val dateCreated: Date
)