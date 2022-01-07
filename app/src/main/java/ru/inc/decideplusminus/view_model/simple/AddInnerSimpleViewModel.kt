package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.ui.base.BaseViewModel

class AddInnerSimpleViewModel: BaseViewModel<AddInnerSimpleViewState, AddInnerSimpleRepository>(SimpleRepositoryImpl) {

    fun plus(solutionId: Long?, argumentLvl: Int) {
        compositeDisposable.add(repository.plus(solutionId, argumentLvl).subscribe {
            mutableLiveData.postValue(AddInnerSimpleViewState.completedAdd)
        })
    }

    fun minus(solutionId: Long?, argumentLvl: Int) {
        compositeDisposable.add(repository.minus(solutionId, argumentLvl).subscribe {
            mutableLiveData.postValue(AddInnerSimpleViewState.completedAdd)
        })
    }
}