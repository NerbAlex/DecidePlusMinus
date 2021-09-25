package ru.inc.decideplusminus.di.components

import dagger.Component
import ru.inc.decideplusminus.di.factory.ViewModelsModule
import ru.inc.decideplusminus.di.module.AppModule
import ru.inc.decideplusminus.di.module.DataBaseModule
import ru.inc.decideplusminus.di.module.RepositoryModule
import ru.inc.decideplusminus.ui.simple.SimpleFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelsModule::class,
        RepositoryModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent {

    fun inject(simpleFragment: SimpleFragment)
}