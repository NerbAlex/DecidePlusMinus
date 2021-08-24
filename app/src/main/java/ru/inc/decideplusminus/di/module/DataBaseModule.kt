package ru.inc.decideplusminus.di.module

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.inc.decideplusminus.model.database.DecideDataBase
import ru.inc.decideplusminus.ui.MyApp
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun dbProvide(app: MyApp) = Room.databaseBuilder(app, DecideDataBase::class.java, DecideDataBase.NAME).build()


}