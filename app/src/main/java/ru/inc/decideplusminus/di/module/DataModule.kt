package ru.inc.decideplusminus.di.module

import dagger.Binds
import dagger.Module
import ru.inc.decideplusminus.data.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.domain.interactor.SimpleRepository
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun provideSimpleRepository(simpleRepository: SimpleRepositoryImpl): SimpleRepository
}