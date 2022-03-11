package ru.inc.decideplusminus.presentation.view_model.simple.create_simple

import io.reactivex.rxjava3.core.Completable

interface CreateSimpleRepository {
    fun createSimpleSolution(name: String): Completable
}