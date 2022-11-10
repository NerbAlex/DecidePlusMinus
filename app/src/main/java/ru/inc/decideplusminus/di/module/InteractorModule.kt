package ru.inc.decideplusminus.di.module

import dagger.Binds
import dagger.Module
import ru.inc.decideplusminus.domain.interactor.CreateSimpleSolutionUseCaseImpl
import ru.inc.decideplusminus.domain.interactor.InsertSolutionToSimpleDetailsUseCaseImpl
import ru.inc.decideplusminus.domain.interactor.SimpleDetailsUseCaseImpl
import ru.inc.decideplusminus.domain.interactor.SimpleMainPageInteractorImpl
import ru.inc.decideplusminus.presentation.view_model.simple.SimpleMainPageInteractor
import ru.inc.decideplusminus.presentation.view_model.simple.create_simple.CreateSolutionUseCase
import ru.inc.decideplusminus.presentation.view_model.simple.insert_to_simple.InsertSolutionUseCase
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsUseCase
import javax.inject.Singleton

@Module
interface InteractorModule {

    @Binds
    @Singleton
    fun bindSimpleMainPageInteractor(simpleMainPageInteractor: SimpleMainPageInteractorImpl)
            : SimpleMainPageInteractor

    @Binds
    fun bindInsertSolutionToSimpleDetailsUseCase(insertSolutionUseCase: InsertSolutionToSimpleDetailsUseCaseImpl)
            : InsertSolutionUseCase


    @Binds
    fun bindSimpleDetailsUseCase(simpleDetailsUseCase: SimpleDetailsUseCaseImpl)
            : SimpleDetailsUseCase

    @Binds
    fun bindCreateSimpleSolutionUseCase(createSolutionUseCase: CreateSimpleSolutionUseCaseImpl)
            : CreateSolutionUseCase
}