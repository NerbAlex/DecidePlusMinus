package ru.inc.decideplusminus.presentation.view_model.simple

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewModel
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleMainPageViewModel @Inject constructor(
    private val simpleMainPageInteractor: SimpleMainPageInteractor
) : BaseViewModel<SimpleMainPageViewState>() {

    fun downloadData() {
        bench("viewModel init") {
            simpleMainPageInteractor.getData().subscribe({ listSimpleVo ->
                bench("viewModel success") {
                    mutableLiveData.postValue(SimpleMainPageViewState.Success(listSimpleVo))
                }
            }, {}).addDisposable()
        }
    }
}