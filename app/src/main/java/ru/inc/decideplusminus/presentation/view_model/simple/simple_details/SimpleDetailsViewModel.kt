package ru.inc.decideplusminus.presentation.view_model.simple.simple_details

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewModel
import ru.inc.decideplusminus.domain.interactor.SimpleRepository
import javax.inject.Inject

class SimpleDetailsViewModel @Inject constructor(
    private val repository: SimpleRepository
) : BaseViewModel<SimpleDetailsViewState>() {

    fun searchById(id: Long) {
        compositeDisposable.add(repository.getSimpleDetailsSolutions(id).subscribe({
            mutableLiveData.postValue(SimpleDetailsViewState.SuccessLists(it.first, it.second))
        }, {}))
    }
}