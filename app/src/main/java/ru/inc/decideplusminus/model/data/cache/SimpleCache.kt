package ru.inc.decideplusminus.model.data.cache

import ru.inc.decideplusminus.model.database.DecideDataBase
import ru.inc.decideplusminus.model.repositories.SimpleLocalDataSource

class SimpleCache(private val db: DecideDataBase): SimpleLocalDataSource {
}