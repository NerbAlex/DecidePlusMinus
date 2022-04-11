package ru.inc.decideplusminus.presentation.ui.events

import android.content.Context
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiEvents @Inject constructor() {

    private val uiEvents: PublishSubject<UiEvent> = PublishSubject.create()

    fun Context.subscribeUiEvents(result: (UiEvent) -> Unit): Disposable = uiEvents.subscribe {
        result.invoke(it)
    }

    fun Context.sendEvent(event: UiEvent) {
        uiEvents.onNext(event)
    }
}

sealed class UiEvent {
    object ReloadMainPage: UiEvent()
}
