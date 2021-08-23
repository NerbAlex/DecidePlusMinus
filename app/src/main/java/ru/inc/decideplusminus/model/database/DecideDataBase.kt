package ru.inc.decideplusminus.model.database

import androidx.room.Database
import ru.inc.decideplusminus.model.entity.DecideEntity

@Database(entities = [DecideEntity::class], version = 1)
abstract class DecideDataBase {
}