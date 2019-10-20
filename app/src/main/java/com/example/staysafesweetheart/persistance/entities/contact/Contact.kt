package com.example.staysafesweetheart.persistance.entities.contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val email: String,
    @ColumnInfo(name = "phone_number") val phoneNumber: String,
    val position: Int = -1
)