package ru.inc.decideplusminus.frameworks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.inc.decideplusminus.data.models.dto.SimpleSolutionEntity

@Database(
    entities = [
        SimpleSolutionEntity::class
    ],
    version = 1, exportSchema = true
)
abstract class SolutionDataBase : RoomDatabase() {

    companion object {
        const val NAME = "database_decide_1"
    }

    abstract fun decideDao(): SolutionDao
}