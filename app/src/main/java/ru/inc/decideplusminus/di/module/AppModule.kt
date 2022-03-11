package ru.inc.decideplusminus.di.module

import dagger.Module
import dagger.Provides
import ru.inc.decideplusminus.presentation.ui.MyApp
import javax.inject.Singleton

@Module
class AppModule(val app: MyApp) {

    @Provides
    @Singleton
    fun appProvide(): MyApp = app
}