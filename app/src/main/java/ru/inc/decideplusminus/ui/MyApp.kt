package ru.inc.decideplusminus.ui

import android.app.Application
import ru.inc.decideplusminus.di.components.AppComponent
import ru.inc.decideplusminus.di.components.DaggerAppComponent
import ru.inc.decideplusminus.di.module.AppModule

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
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }
}