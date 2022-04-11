package ru.inc.decideplusminus.frameworks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.inc.decideplusminus.data.models.dto.SimpleEntity

@Database(
    entities = [
        SimpleEntity::class
    ],
    version = 1, exportSchema = true
)
abstract class SolutionDataBase : RoomDatabase() {

    companion object {
        const val NAME = "database_decide_2"
    }

    abstract fun decideDao(): SolutionDao
}