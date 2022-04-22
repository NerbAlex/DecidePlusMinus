package ru.inc.decideplusminus.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val parentId: Long,
    val type: Int,
    val name: String,
    val argumentLvl: Int,
    val argumentDescription: String
)
