package ru.inc.decideplusminus.frameworks.base.base_presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlin.reflect.KFunction1

abstract class BaseViewModel<State>(
    protected val mutableLiveData: MutableLiveData<State> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    open fun getData(): LiveData<State> = mutableLiveData

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun Disposable.addDisposable() = compositeDisposable.add(this)
}