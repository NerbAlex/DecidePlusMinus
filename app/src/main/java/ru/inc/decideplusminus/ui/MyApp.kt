package ru.inc.decideplusminus.ui

import android.app.Application

class MyApp: Application() {

    companion object {
        @get: Synchronized
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this


    }
}