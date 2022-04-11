package ru.inc.decideplusminus.data.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.inc.decideplusminus.frameworks.database.SolutionDataBase
import ru.inc.decideplusminus.data.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.frameworks.base.base_data.SimpleMapper
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleLocalDataSourceImpl @Inject constructor(private val db: SolutionDataBase) : SimpleMapper(),
    SimpleLocalDataSource {

    override fun getAll(): Single<List<BaseSimpleItem>> =
        bench("dataSource") { db.decideDao().getAll().toSimpleVoList().subscribeOn(Schedulers.io()) }  // TODO проверить в каком потоке мапится simpleVoList и если подписываться во вьюмодели


    override fun updateData(baseSimpleItem: BaseSimpleItem): Completable {
        TODO("Not yet implemented")
    }

    override fun create(simpleSolution: SimpleVO): Completable {
        return db.decideDao().insert(simpleSolution.toEntity())
    }
}