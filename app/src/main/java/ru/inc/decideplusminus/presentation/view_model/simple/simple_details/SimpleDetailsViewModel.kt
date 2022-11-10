package ru.inc.decideplusminus.presentation.view_model.simple.simple_details

import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseViewModel
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleDetailsViewModel @Inject constructor(
    private val simpleDetailsUseCase: SimpleDetailsUseCase
) : BaseViewModel<SimpleDetailsViewState>() {

    fun searchById(id: Long) {
        compositeDisposable.add(
            simpleDetailsUseCase.getSimpleDetailsSolutions(id).subscribe({ // todo ext safeSubscribe под капотом addDisposable
                mutableLiveData.postValue(SimpleDetailsViewState.SuccessLists(it.first, it.second))
            }, {})
        )
    }

    fun delete(vo: SimpleDetailsVO) = with(vo) {
        simpleDetailsUseCase.deleteSimpleDetail(vo).subscribe { // todo пересчитывать при удалении процент в паренте, завести юскейс?
            searchById(parentId)
        }.addDisposable()
    }
}