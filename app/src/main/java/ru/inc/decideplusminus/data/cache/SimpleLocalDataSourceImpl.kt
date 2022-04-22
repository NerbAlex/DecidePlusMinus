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
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleLocalDataSourceImpl @Inject constructor(private val db: SolutionDataBase) : SimpleMapper(),
    SimpleLocalDataSource {

    override fun getAll(): Single<List<BaseSimpleItem>> =
        bench("dataSource") {
            db.decideDao().getAll().toSimpleVoList().subscribeOn(Schedulers.io())
        }


    override fun updateData(baseSimpleItem: BaseSimpleItem): Completable {
        TODO("Not yet implemented")
    }

    override fun create(simpleSolution: SimpleVO): Completable =
        db.decideDao().insert(simpleSolution.toEntity()).subscribeOn(Schedulers.io())


    override fun getDetailsById(parentId: Long): Single<SimpleDetailsViewState> {
//        db.decideDao().getDetailsById(parentId).toDetailsVOList().flatMap {
//
//        }
        TODO("Not yet implemented")
    }

    override fun insertDetails(detailsVO: SimpleDetailsVO): Completable {
        TODO("Not yet implemented")
    }
}