package ru.inc.decideplusminus.view_model.simple.details

import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.ui.base.BaseViewModel

class SimpleDetailsViewModel: BaseViewModel<SimpleDetailsViewState, SimpleDetailsRepository>(SimpleRepositoryImpl) {

    fun searchById(id: Long) {
        val a = id
        compositeDisposable.add(repository.searchSolutionById(id).subscribe({
            mutableLiveData.postValue(it)
        }, {}))
    }
}