package ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple

import io.reactivex.rxjava3.core.Completable

interface InsertSolutionUseCase {
    fun plus(solutionId: Long, argumentLvl: Int, name: String): Completable
    fun minus(solutionId: Long, argumentLvl: Int, name: String): Completable
}