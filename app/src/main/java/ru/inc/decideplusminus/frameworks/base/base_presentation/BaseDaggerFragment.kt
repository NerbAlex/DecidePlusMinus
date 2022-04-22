package ru.inc.decideplusminus.frameworks.base.base_presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.inc.decideplusminus.presentation.ui.MyApp
import ru.inc.decideplusminus.presentation.ui.events.UiEvents
import javax.inject.Inject

abstract class BaseDaggerFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var uiEvents: UiEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}

abstract class BaseDaggerBottomSheetFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var uiEvents: UiEvents

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}