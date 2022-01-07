package ru.inc.decideplusminus.view_model.simple

import io.reactivex.rxjava3.core.Completable

interface AddInnerSimpleRepository {
    fun plus(solutionId: Long?, argumentLvl: Int): Completable
    fun minus(solutionId: Long?, argumentLvl: Int): Completable
}