package com.example.staysafesweetheart.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.staysafesweetheart.persistance.entities.template.message.TemplateMessage


@Dao
interface TemplateMessageDao {
    @Insert
    suspend fun insertTemplateMessage(templateMessage: TemplateMessage)

    @Query("DELETE FROM template_messages")
    suspend fun clearTable()

    @Query("SELECT * FROM template_messages LIMIT 1")
    fun getLatestTemplateMessage(): LiveData<TemplateMessage>
}