package ru.inc.decideplusminus.model.data.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.model.database.SolutionDataBase
import ru.inc.decideplusminus.model.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.ui.simple.BaseSimpleSolutionItem
import javax.inject.Singleton

@Singleton
class SimpleCache(private val db: SolutionDataBase): SimpleLocalDataSource {

    override fun downloadData(): Single<List<BaseSimpleSolutionItem>> {

        TODO("Not yet implemented")
    }

    override fun updateData(baseSimpleSolutionItem: BaseSimpleSolutionItem): Completable {
        TODO("Not yet implemented")
    }
}