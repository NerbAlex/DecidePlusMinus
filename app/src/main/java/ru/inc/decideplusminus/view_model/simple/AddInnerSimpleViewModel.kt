package ru.inc.decideplusminus.view_model.simple

import ru.inc.decideplusminus.model.repositories.SimpleRepositoryImpl
import ru.inc.decideplusminus.ui.base.BaseViewModel

class AddInnerSimpleViewModel: BaseViewModel<AddInnerSimpleViewState, AddInnerSimpleRepository>(SimpleRepositoryImpl) {
}