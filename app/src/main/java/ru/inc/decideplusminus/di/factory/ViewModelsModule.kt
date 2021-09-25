package ru.inc.decideplusminus.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.inc.decideplusminus.view_model.simple.SimpleViewModel

@Module
interface ViewModelsModule {

    /**
     * [Binds] - Принимает только один аргумент
     */
    @Binds
    fun bindViewModuleFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SimpleViewModel::class)
    fun mainViewModel(mainViewModel: SimpleViewModel): ViewModel
}