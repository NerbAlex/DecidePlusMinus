package ru.inc.decideplusminus.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.inc.decideplusminus.data.cache.SimpleLocalDataSourceImpl
import ru.inc.decideplusminus.data.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.frameworks.database.SolutionDataBase
import ru.inc.decideplusminus.presentation.ui.MyApp
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun dbProvide(app: MyApp) = Room.databaseBuilder(app, SolutionDataBase::class.java, SolutionDataBase.NAME).build()

    @Provides
    fun simpleLocalDataSource(dataBase: SolutionDataBase): SimpleLocalDataSource =
        SimpleLocalDataSourceImpl(db = dataBase)
}