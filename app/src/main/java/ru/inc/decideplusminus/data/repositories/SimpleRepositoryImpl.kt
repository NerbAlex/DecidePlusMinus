package ru.inc.decideplusminus.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewState
import ru.inc.decideplusminus.domain.interactor.SimpleRepository
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

class SimpleRepositoryImpl @Inject constructor(
    private val localDataSource: SimpleLocalDataSource
) : SimpleRepository {

    //TODO Создать Use Cases и разгрузить репозиторий, вся логика в юскейсах, реп только решает куда идти за данными(runtime, db, remote)

    override val simpleMainPagePS: PublishSubject<SimpleMainPageViewState> = PublishSubject.create() // TODO похоже он нужен для интерактора или лучше при сохранении возвращать гготовый список

    override fun getSimpleSolutions(): Single<List<BaseSimpleItem>> {
        return bench("Repository") { localDataSource.getAll()  }
    }

    override fun getSimpleSolution(id: Long): Single<BaseSimpleItem> {
        return Single.fromCallable {
            listSolution.find { it.id == id }
        }
    }

    override fun getSimpleDetailSolution(): Single<SimpleDetailsVO> {
        TODO("Not yet implemented")
    }

    override fun getSimpleDetailsSolutions(parentId: Long): Single<List<SimpleDetailsVO>> {
        TODO("Not yet implemented")
    }

    override fun updateSimpleVo(newVO: SimpleVO): Completable {
        return Completable.complete()// TODO
    }

    override fun insertSimpleDetail(vo: SimpleDetailsVO): Completable {
        return Completable.complete()// TODO
    }

    init {
//        downloadData()
    }

    private val listInnerSolution = mutableMapOf<Long, MutableList<SimpleDetailsVO>>()
    private val listSolution = mutableListOf<BaseSimpleItem>()

    /**
     * дергается только в этом классе, например: при инициализации репозитория или когда надо обновить стейт SimpleMainPage
     */
    private fun downloadData() {
        val newList: MutableList<BaseSimpleItem> = mutableListOf()
        newList.addAll(listSolution)
        simpleMainPagePS.onNext(SimpleMainPageViewState.Success(newList))
    }

    // TODO в useCase
    override fun createSimpleSolution(simpleSolution: SimpleVO): Completable {
        listSolution.add(simpleSolution)
        downloadData()
        localDataSource.create(simpleSolution)
        return Completable.complete()
    }

    fun searchDetailsSolutionsById(id: Long): Single<SimpleDetailsViewState> {
        // TODO заместо листов дергать базу в зипе или конкате, вспомнить RX
        val allList = listInnerSolution[id]
        val positiveList = allList?.filter { it.type == BaseSimpleItem.DETAILS_POSITIVE }
        val negativeList = allList?.filter { it.type == BaseSimpleItem.DETAILS_NEGATIVE }
        return Single.just(
            SimpleDetailsViewState.SuccessLists(
                positiveList = positiveList,
                negativeList = negativeList
            )
        )
    }
}