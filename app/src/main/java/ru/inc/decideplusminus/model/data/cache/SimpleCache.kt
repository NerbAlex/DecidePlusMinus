package ru.inc.decideplusminus.model.data.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.model.database.SolutionDataBase
import ru.inc.decideplusminus.model.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.ui.base.BaseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimpleCache(private val db: SolutionDataBase): SimpleLocalDataSource {

    override fun downloadData(): Single<List<BaseItem>> {

        TODO("Not yet implemented")
    }

    override fun updateData(baseItem: BaseItem): Completable {
        TODO("Not yet implemented")
    }
}