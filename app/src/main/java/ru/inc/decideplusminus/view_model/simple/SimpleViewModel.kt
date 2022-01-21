package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.ui.base.BaseViewModel

class SimpleViewModel : BaseViewModel<SimpleViewState, SimpleRepository>(SimpleRepositoryImpl) {

    init {
        compositeDisposable.add(repository.simplePS.subscribe({ state ->
            mutableLiveData.postValue(state)
        }, {}))
    }

    fun downloadData() {
        repository.downloadData()
    }
}