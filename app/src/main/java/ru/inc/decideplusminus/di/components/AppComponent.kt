package ru.inc.decideplusminus.di.components

import dagger.Component
import ru.inc.decideplusminus.di.module.AppModule
import ru.inc.decideplusminus.view_model.simple.SimpleViewModel
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(simpleViewModel: SimpleViewModel)
}