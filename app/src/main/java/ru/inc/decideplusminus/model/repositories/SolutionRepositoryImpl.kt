package ru.inc.decideplusminus.model.repositories

import ru.inc.decideplusminus.view_model.simple.SolutionRepository

class SolutionRepositoryImpl(val simple: SimpleLocalDataSource, val complex: ComplexLocalDataSource): SolutionRepository {
}