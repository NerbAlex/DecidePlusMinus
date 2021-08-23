package ru.inc.decideplusminus.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.inc.decideplusminus.ui.view_model.simple.ViewState

abstract class BaseViewModel<S: ViewState>(
    protected val mutableLiveData: MutableLiveData<S> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),

    ) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}