package ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewModel
import javax.inject.Inject

class InsertSolutionToSimpleDetailsVM @Inject constructor(
    private val useCase: InsertSolutionUseCase
) :
    BaseViewModel<InsertSolutionToSimpleDetailsViewState>() {

    fun plus(solutionId: Long?, argumentLvl: Int, name: String) {
        if (solutionId == null) return

        useCase.plus(solutionId, argumentLvl, name).subscribe {
            mutableLiveData.postValue(InsertSolutionToSimpleDetailsViewState.CompletedAddDetails)
        }.addDisposable()
    }

    fun minus(solutionId: Long?, argumentLvl: Int, name: String) {
        if (solutionId == null) return

        useCase.minus(solutionId, argumentLvl, name).subscribe {
            mutableLiveData.postValue(InsertSolutionToSimpleDetailsViewState.CompletedAddDetails)
        }.addDisposable()
    }
}
