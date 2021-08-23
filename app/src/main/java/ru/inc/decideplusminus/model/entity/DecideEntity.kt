package ru.inc.decideplusminus.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DecideEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val importance: Int
)
