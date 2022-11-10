package ru.inc.decideplusminus.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.domain.interactor.SimpleRepository
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleRepositoryImpl @Inject constructor(
    private val localDataSource: SimpleLocalDataSource
) : SimpleRepository {

    //TODO Создать Use Cases и разгрузить репозиторий, вся логика в юскейсах, реп только решает куда идти за данными(runtime, db, remote)

    override fun getSimpleSolutions(): Single<List<BaseSimpleItem>> = bench("Repository") {
        localDataSource.getAll()
    }

    override fun getSimpleSolution(id: Long): Single<SimpleVO> {
        return localDataSource.getSimple(id)
    }

    override fun getSimpleDetailSolution(): Single<SimpleDetailsVO> {
        TODO("Not yet implemented")
    }

    override fun getSimpleDetailsSolutions(parentId: Long): Single<Pair<List<SimpleDetailsVO>, List<SimpleDetailsVO>>> =
        localDataSource.getDetailsById(parentId)

    override fun updateSimpleVo(newVO: SimpleVO): Completable {
        return localDataSource.insert(newVO)
    }

    override fun insertSimpleDetail(vo: SimpleDetailsVO): Completable {
        return localDataSource.insertDetails(vo)
    }

    override fun delete(id: Long): Completable {
        return localDataSource.delete(id)
    }

    override fun deleteSimpleDetail(id: Long): Completable {
        return localDataSource.deleteSimpleDetail(id)
    }

    override fun createSimpleSolution(simpleSolution: SimpleVO): Completable = bench("create") {
        localDataSource.create(simpleSolution)
    }
}