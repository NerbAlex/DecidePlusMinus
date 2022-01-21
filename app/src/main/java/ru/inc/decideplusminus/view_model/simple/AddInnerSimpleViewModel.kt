package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.ui.base.BaseViewModel

class AddInnerSimpleViewModel: BaseViewModel<AddInnerSimpleViewState, AddInnerSimpleRepository>(SimpleRepositoryImpl) {

    fun plus(solutionId: Long?, argumentLvl: Int, name: String) {
        if (solutionId == null) return
        compositeDisposable.add(repository.plus(solutionId, argumentLvl, name).subscribe {
            mutableLiveData.postValue(AddInnerSimpleViewState.CompletedAdd)
        })
    }

    fun minus(solutionId: Long?, argumentLvl: Int, name: String) {
        if (solutionId == null) return
        compositeDisposable.add(repository.minus(solutionId, argumentLvl, name).subscribe {
            mutableLiveData.postValue(AddInnerSimpleViewState.CompletedAdd)
        })
    }
}