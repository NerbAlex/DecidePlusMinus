package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.ui.base.BaseViewModel

class SimpleViewModel : BaseViewModel<SimpleViewState, SimpleRepository>(SimpleRepositoryImpl) {

    fun start() {
        repository.downloadData()
        compositeDisposable.add(repository.downloadData().subscribe({
            mutableLiveData.postValue(it)
        }, {}))
    }
}