package ru.inc.decideplusminus.presentation.view_model.simple.create_simple

import io.reactivex.rxjava3.core.Completable

interface CreateSolutionUseCase {
    fun createSimpleSolution(name: String): Completable
}