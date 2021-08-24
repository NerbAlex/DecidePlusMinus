package ru.inc.decideplusminus.model.data.cache

import ru.inc.decideplusminus.model.database.DecideDataBase
import ru.inc.decideplusminus.model.repositories.SimpleLocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimpleCache @Inject constructor(private val db: DecideDataBase): SimpleLocalDataSource {
}