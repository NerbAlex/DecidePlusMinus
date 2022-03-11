package ru.inc.decideplusminus.presentation.view_model.simple.create_simple

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewModel
import javax.inject.Inject

class CreateSimpleVM @Inject constructor(
    private val createSolutionUseCase: CreateSolutionUseCase
) : BaseViewModel<CreateSimpleViewState>() {

    fun createSolution(name: String) {
        compositeDisposable.add(createSolutionUseCase.createSimpleSolution(name).subscribe {
            mutableLiveData.postValue(CreateSimpleViewState.Created)
        })
    }
}