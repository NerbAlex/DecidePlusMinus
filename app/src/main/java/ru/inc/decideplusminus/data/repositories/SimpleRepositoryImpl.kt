package ru.inc.decideplusminus.data.repositories

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.ui.simple.details.SimpleDetailsVO
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageViewState
import ru.inc.decideplusminus.domain.interactor.SimpleRepository
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState
import javax.inject.Inject

class SimpleRepositoryImpl @Inject constructor(

) : SimpleRepository {

    //TODO Создать Use Cases и разгрузить репозиторий, вся логика в юскейсах, реп только решает куда идти за данными(runtime, db, remote)

    override val simpleMainPagePS: PublishSubject<SimpleMainPageViewState> = PublishSubject.create()

    override fun getSimpleSolutions(): Single<List<SimpleVO>> {
        return Single.just(emptyList())
    }

    override fun getSimpleSolution(id: Long): Single<SimpleVO> {
        TODO("Not yet implemented")
    }

    override fun getSimpleDetailSolution(): Single<SimpleDetailsVO> {
        TODO("Not yet implemented")
    }

    override fun getSimpleDetailsSolutions(parentId: Long): Single<List<SimpleDetailsVO>> {
        TODO("Not yet implemented")
    }

    override fun updateSimpleVo(newVO: SimpleVO): Completable {
        TODO("Not yet implemented")
    }

    override fun insertSimpleDetail(vo: SimpleDetailsVO): Completable {
        TODO("Not yet implemented")
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