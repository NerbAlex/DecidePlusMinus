package ru.inc.decideplusminus.data.cache

import ru.inc.decideplusminus.frameworks.database.SolutionDataBase
import ru.inc.decideplusminus.data.repositories.ComplexLocalDataSource

class ComplexCache(private val db: SolutionDataBase): ComplexLocalDataSource {
}