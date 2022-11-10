package ru.inc.decideplusminus.presentation.view_model.simple

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO

interface SimpleMainPageInteractor {
    fun getData(): Single<List<BaseSimpleItem>>
    fun delete(id: Long): Completable
}