package ru.inc.decideplusminus.data.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.frameworks.database.SolutionDataBase
import ru.inc.decideplusminus.data.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem
import javax.inject.Singleton

@Singleton
class SimpleCache(private val db: SolutionDataBase): SimpleLocalDataSource {

    override fun downloadData(): Single<List<BaseSimpleItem>> {

        TODO("Not yet implemented")
    }

    override fun updateData(baseSimpleItem: BaseSimpleItem): Completable {
        TODO("Not yet implemented")
    }
}