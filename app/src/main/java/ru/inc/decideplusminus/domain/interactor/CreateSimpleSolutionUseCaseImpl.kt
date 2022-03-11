package ru.inc.decideplusminus.domain.interactor

import io.reactivex.rxjava3.core.Completable
import ru.inc.decideplusminus.presentation.ui.simple.BaseSimpleItem
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO
import ru.inc.decideplusminus.presentation.view_model.simple.create_simple.CreateSolutionUseCase
import javax.inject.Inject

class CreateSimpleSolutionUseCaseImpl @Inject constructor(
    private val repository: SimpleRepository
): CreateSolutionUseCase {

    override fun createSimpleSolution(name: String): Completable {
        val newVO = SimpleVO(
            id = System.currentTimeMillis(),
            type = BaseSimpleItem.NEUTRAL,
            name = name,
            percent = ""
        )
        return repository.createSimpleSolution(newVO)
    }
}