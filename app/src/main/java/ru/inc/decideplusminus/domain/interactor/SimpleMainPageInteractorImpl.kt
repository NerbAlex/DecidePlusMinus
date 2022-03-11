package ru.inc.decideplusminus.domain.interactor

import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageInteractor
import javax.inject.Inject

/**
 * Вся логика по проверке первого входа, сборе всех VO и прочее
 */
class SimpleMainPageInteractorImpl @Inject constructor(
    private val repository: SimpleRepository
): SimpleMainPageInteractor {

    override fun getData(): Single<List<SimpleVO>> {
        return repository.getSimpleSolutions()
    }

    // 1. чекаем ViewObjectStorage, если там нет, собираем список VO, если есть, отдаем во вьюмодель

    // ViewObjectStorage @singleton и там хранит готовый список всех актуальных VO

    // 2. ViewObjectStorage пустой, идем в репозиторий sharedDataSource, берем настройки, потом собираем нужный список VO
    // потом фильтруем и собираем его по бизнес логике, отдаем во вьюмодельи в ViewObjectStorage
}