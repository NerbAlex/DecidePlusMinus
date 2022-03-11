package ru.inc.decideplusminus.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.inc.decideplusminus.data.cache.SimpleCache
import ru.inc.decideplusminus.frameworks.database.SolutionDataBase
import ru.inc.decideplusminus.data.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.presentation.ui.MyApp
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun dbProvide(app: MyApp) = Room.databaseBuilder(app, SolutionDataBase::class.java, SolutionDataBase.NAME).build()

    @Provides
    @Singleton
    fun simpleLocalDataSource(dataBase: SolutionDataBase): SimpleLocalDataSource = SimpleCache(db = dataBase)
}