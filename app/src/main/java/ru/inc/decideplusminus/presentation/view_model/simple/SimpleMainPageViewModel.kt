package ru.inc.decideplusminus.presentation.view_model.simple

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewModel
import javax.inject.Inject

class SimpleMainPageViewModel @Inject constructor(
    private val simpleMainPageInteractor: SimpleMainPageInteractor
) : BaseViewModel<SimpleMainPageViewState>() {

    init {
        simpleMainPageInteractor.getData().subscribe({ listSimpleVo ->
            mutableLiveData.postValue(SimpleMainPageViewState.Success(listSimpleVo))
        }, {}).addDisposable()
    }
}