package ru.inc.decideplusminus.di.module

import dagger.Module
import dagger.Provides
import ru.inc.decideplusminus.model.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.model.repositories.SimpleSolutionRepositoryImpl
import ru.inc.decideplusminus.view_model.simple.SimpleViewState
import ru.inc.decideplusminus.view_model.simple.SolutionRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun repositoryProvide(simple: SimpleLocalDataSource): SolutionRepository<SimpleViewState> =
        SimpleSolutionRepositoryImpl(simple)


}