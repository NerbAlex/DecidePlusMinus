package ru.inc.decideplusminus.data.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.inc.decideplusminus.frameworks.database.SolutionDataBase
import ru.inc.decideplusminus.data.repositories.SimpleLocalDataSource
import ru.inc.decideplusminus.frameworks.base.base_data.SimpleMapper
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleLocalDataSourceImpl @Inject constructor(private val db: SolutionDataBase) : SimpleMapper(),
    SimpleLocalDataSource {

    override fun getAll(): Single<List<BaseSimpleItem>> =
        bench("dataSource") {
            db.decideDao().getAll().toVoList().subscribeOn(Schedulers.io())
        }

    override fun insert(simpleSolution: SimpleVO): Completable {
        return db.decideDao().insert(simpleSolution.toEntity()).subscribeOn(Schedulers.io())
    }

    override fun create(simpleSolution: SimpleVO): Completable =
        db.decideDao().insert(simpleSolution.toEntity()).subscribeOn(Schedulers.io())

    override fun getDetailsById(parentId: Long): Single<Pair<List<SimpleDetailsVO>, List<SimpleDetailsVO>>> =
        db.decideDao().getDetailsById(parentId).flatMap { detailsEntityList ->
            val positiveList = detailsEntityList.toPositiveVoList()
            val negativeList = detailsEntityList.toNegativeVoList()
            return@flatMap Single.just(Pair(positiveList, negativeList))
        }.subscribeOn(Schedulers.io())


    override fun insertDetails(detailsVO: SimpleDetailsVO): Completable {
        return db.decideDao()
            .insertDetails(detailsVO.toEntity())
            .subscribeOn(Schedulers.io())
    }

    override fun getSimple(id: Long): Single<SimpleVO> {
        return db.decideDao()
            .getSimple(id)
            .map { it.toVO() }
            .subscribeOn(Schedulers.io())
    }

    override fun delete(id: Long): Completable = with(db.decideDao()) {
        return deleteDetails(id)
            .andThen(
                deleteSimple(id)
            ).subscribeOn(Schedulers.io())
    }

    override fun deleteSimpleDetail(id: Long): Completable {
        return db.decideDao().deleteDetail(id).subscribeOn(Schedulers.io())
    }
}