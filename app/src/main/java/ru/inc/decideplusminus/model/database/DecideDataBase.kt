package ru.inc.decideplusminus.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.inc.decideplusminus.model.entity.DecideEntity

@Database(entities = [DecideEntity::class], version = 1)
abstract class DecideDataBase: RoomDatabase() {

    companion object{
        const val NAME = "database_decide_1"
    }

    abstract fun decideDao(): DecideDao
}