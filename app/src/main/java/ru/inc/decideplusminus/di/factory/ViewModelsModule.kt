package ru.inc.decideplusminus.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewModel
import ru.inc.decideplusminus.presentation.view_model.simple.create_simple.CreateSimpleVM
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionToSimpleDetailsVM
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewModel

@Module
interface ViewModelsModule {

    /**
     * [Binds] - Принимает только один аргумент
     */
    @Binds
    fun bindViewModuleFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SimpleMainPageViewModel::class)
    fun bindSimpleMainPageViewModel(simpleMainPageViewModel: SimpleMainPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SimpleDetailsViewModel::class)
    fun bindSimpleDetailsViewModel(simpleDetailsViewModel: SimpleDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateSimpleVM::class)
    fun bindCreateSimpleViewModel(createSimpleVM: CreateSimpleVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InsertSolutionToSimpleDetailsVM::class)
    fun bindInsertSolutionToSimpleViewModel(insertSolutionToSimpleDetailsVM: InsertSolutionToSimpleDetailsVM): ViewModel
}
