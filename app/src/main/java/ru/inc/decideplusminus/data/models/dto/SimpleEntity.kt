package ru.inc.decideplusminus.data.models.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SimpleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: Int,
    val name: String,
    val percent: String,
    val positiveCount: Int,
    val negativeCount: Int
)
