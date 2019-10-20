package com.example.staysafesweetheart.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.staysafesweetheart.persistance.entities.template.message.TemplateMessage


@Dao
interface TemplateMessageDao {
    @Insert
    suspend fun insertTemplateMessage(templateMessage: TemplateMessage)

    @Query("SELECT * FROM template_messages WHERE dateCreated >= (SELECT max(dateCreated) FROM template_messages) LIMIT 1")
    fun getLatestTemplateMessage(): TemplateMessage
}