package ru.inc.decideplusminus.model.data.cache

import ru.inc.decideplusminus.model.database.SolutionDataBase
import ru.inc.decideplusminus.model.repositories.ComplexLocalDataSource

class ComplexCache(private val db: SolutionDataBase): ComplexLocalDataSource {
}