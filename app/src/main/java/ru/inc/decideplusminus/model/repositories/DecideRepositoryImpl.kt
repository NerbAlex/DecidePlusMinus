package ru.inc.decideplusminus.model.repositories

import ru.inc.decideplusminus.view_model.simple.DecideRepository

class DecideRepositoryImpl(val simple: SimpleLocalDataSource, val complex: ComplexLocalDataSource): DecideRepository {
}