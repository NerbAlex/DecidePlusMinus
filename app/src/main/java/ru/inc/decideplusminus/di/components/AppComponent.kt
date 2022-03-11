package ru.inc.decideplusminus.di.components

import dagger.Component
import ru.inc.decideplusminus.di.factory.ViewModelsModule
import ru.inc.decideplusminus.di.module.AppModule
import ru.inc.decideplusminus.di.module.DataBaseModule
import ru.inc.decideplusminus.di.module.DataModule
import ru.inc.decideplusminus.di.module.InteractorModule
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseDaggerFragment
import ru.inc.decideplusminus.presentation.ui.BottomSheetInsertSolutionToSimpleDetailsFragment
import ru.inc.decideplusminus.presentation.ui.simple.BottomSheetCreateSimpleFragment
import ru.inc.decideplusminus.presentation.ui.simple.SimpleMainPageFragment
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        InteractorModule::class,
        DataModule::class,
        ViewModelsModule::class,
        DataBaseModule::class
    ]
)
interface AppComponent {
    fun inject(simpleDetailsFragment: SimpleDetailsFragment)
    fun inject(simpleDetailsFragment: SimpleMainPageFragment)
    fun inject(bottomSheetCreateSimpleFragment: BottomSheetCreateSimpleFragment)
    fun inject(bottomSheetInsertSolutionToSimpleDetailsFragment: BottomSheetInsertSolutionToSimpleDetailsFragment)
    fun inject(baseBaseFragment: BaseDaggerFragment)
}