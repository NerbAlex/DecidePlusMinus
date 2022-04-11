package ru.inc.decideplusminus.presentation.view_model.simple

import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem

interface SimpleMainPageInteractor {
    fun getData(): Single<List<BaseSimpleItem>>
}