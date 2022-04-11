package ru.inc.decideplusminus.di.components

import dagger.Component
import ru.inc.decideplusminus.di.factory.ViewModelsModule
import ru.inc.decideplusminus.di.module.AppModule
import ru.inc.decideplusminus.di.module.LocalDataSourceModule
import ru.inc.decideplusminus.di.module.DataModule
import ru.inc.decideplusminus.di.module.InteractorModule
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseDaggerBottomSheetFragment
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseDaggerFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        InteractorModule::class,
        DataModule::class,
        ViewModelsModule::class,
        LocalDataSourceModule::class
    ]
)
interface AppComponent {
    fun inject(baseBaseFragment: BaseDaggerFragment)
    fun inject(baseDaggerBottomSheetFragment: BaseDaggerBottomSheetFragment)
}