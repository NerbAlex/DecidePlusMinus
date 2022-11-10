package ru.inc.decideplusminus.domain.interactor

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageInteractor
import ru.inc.decideplusminus.utils.perfomance.bench
import javax.inject.Inject

/**
 * Вся логика по проверке первого входа, сборе всех VO и прочее
 */
class SimpleMainPageInteractorImpl @Inject constructor(
    private val repository: SimpleRepository
) : SimpleMainPageInteractor {

    override fun getData(): Single<List<BaseSimpleItem>> {
        return bench("interactor") {
            repository.getSimpleSolutions()
        }
    }

    override fun delete(id: Long): Completable {
//        return repository.delete(vo).to {
//            repository.getSimpleSolutions() // todo проверить работаспособность
//        }
        return repository.delete(id)
    }

    // 1. чекаем ViewObjectStorage, если там нет, собираем список VO, если есть, отдаем во вьюмодель

    // ViewObjectStorage @singleton и там хранит готовый список всех актуальных VO

    // 2. ViewObjectStorage пустой, идем в репозиторий sharedDataSource, берем настройки, потом собираем нужный список VO
    // потом фильтруем и собираем его по бизнес логике, отдаем во вьюмодельи в ViewObjectStorage
}