package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.ui.base.BaseViewModel

class CreateSimpleViewModel : BaseViewModel<CreateSimpleViewState, CreateSimpleRepository>(SimpleRepositoryImpl) {

    fun createSolution(name: String) {
        compositeDisposable.add(repository.createSimpleSolution(name).subscribe {
            mutableLiveData.postValue(CreateSimpleViewState.Created)
        })
    }

}