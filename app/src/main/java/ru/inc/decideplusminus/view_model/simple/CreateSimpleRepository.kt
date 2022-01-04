package ru.inc.decideplusminus.view_model.simple

import io.reactivex.rxjava3.core.Completable

interface CreateSimpleRepository {
    fun createSimpleSolution(name: String): Completable
}