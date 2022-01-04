package ru.inc.decideplusminus.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.view_model.simple.SimpleRepository
import ru.inc.decideplusminus.view_model.simple.SimpleViewState

abstract class BaseViewModel<State, Repository>(
    protected val repository: Repository,
    protected val mutableLiveData: MutableLiveData<State> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    open fun getData(): LiveData<State> = mutableLiveData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}