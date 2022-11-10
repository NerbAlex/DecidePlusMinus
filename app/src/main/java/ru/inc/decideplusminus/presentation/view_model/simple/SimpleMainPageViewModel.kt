package ru.inc.decideplusminus.presentation.view_model.simple

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewModel
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleMainPageViewModel @Inject constructor(
    private val simpleMainPageInteractor: SimpleMainPageInteractor
) : BaseViewModel<SimpleMainPageViewState>() {

    init {
        val a = 10
    }

    fun downloadData() {
        bench("viewModel downloadData success") {
            simpleMainPageInteractor.getData().subscribe({ listSimpleVo ->
                mutableLiveData.postValue(SimpleMainPageViewState.Success(listSimpleVo))
            }, {}).addDisposable()
        }
    }

    fun delete(id: Long) {
        bench("viewModel delete success") {
            simpleMainPageInteractor.delete(id).subscribe { ->
                downloadData()
            }.addDisposable()
        }
    }
}