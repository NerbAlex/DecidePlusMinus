package ru.inc.decideplusminus.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<State>(
    protected val mutableLiveData: MutableLiveData<State> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    ) : ViewModel() {

    open fun getData(): LiveData<State> = mutableLiveData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}