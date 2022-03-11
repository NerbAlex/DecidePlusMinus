package ru.inc.decideplusminus.presentation.ui

import android.app.Application
import ru.inc.decideplusminus.di.components.AppComponent
import ru.inc.decideplusminus.di.components.DaggerAppComponent
import ru.inc.decideplusminus.di.module.AppModule
import timber.log.Timber

class MyApp: Application() {

    companion object {
        @get: Synchronized
        lateinit var instance: MyApp
            private set
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}