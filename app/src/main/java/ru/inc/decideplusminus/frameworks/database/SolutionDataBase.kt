package ru.inc.decideplusminus.frameworks.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.inc.decideplusminus.data.models.entities.DetailsEntity
import ru.inc.decideplusminus.data.models.entities.SimpleEntity

@Database(
    entities = [
        SimpleEntity::class,
        DetailsEntity::class
    ],
    version = 1, exportSchema = true
)
abstract class SolutionDataBase : RoomDatabase() {

    companion object {
        const val NAME = "database_decide_11"
    }

    abstract fun decideDao(): SolutionDao
}