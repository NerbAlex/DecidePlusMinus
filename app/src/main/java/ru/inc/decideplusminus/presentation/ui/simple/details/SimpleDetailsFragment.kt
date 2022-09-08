package ru.inc.decideplusminus.presentation.ui.simple.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ru.inc.decideplusminus.databinding.FragmentSimpleDetailsBinding
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseFragment
import ru.inc.decideplusminus.presentation.ui.simple.SimpleAdapter
import ru.inc.decideplusminus.presentation.ui.simple.SimpleAdapterListener
import ru.inc.decideplusminus.presentation.ui.simple.SimpleDetailsListener
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewState
import ru.inc.decideplusminus.presentation.view_model.simple.simple_details.SimpleDetailsViewModel
import ru.inc.decideplusminus.utils.ViewHoldersFactory
import ru.inc.decideplusminus.utils.extensions.toolbarAnimationWithRecyclerView

class SimpleDetailsFragment :
    BaseFragment<FragmentSimpleDetailsBinding, SimpleDetailsViewState>(FragmentSimpleDetailsBinding::inflate) {

    private val navArgs: SimpleDetailsFragmentArgs by navArgs()
    private val viewModel: SimpleDetailsViewModel by lazy { initViewModel() }
    private var adapterPositive: SimpleAdapter? = null
    private var adapterNegative: SimpleAdapter? = null
    private var parentId: Long? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()

        parentId = navArgs.parentId
        parentId?.let(viewModel::searchById)
    }

    private fun listener(vo: SimpleDetailsVO) {

    }

    private fun bindLastItem(lastPosition: Int) {

    }

    private fun initRecyclerViews() {
        adapterPositive = SimpleAdapter(SimpleDetailsListener(::listener))
        binding.recyclerPositive.setHasFixedSize(true) // todo удалить, когда сделаю раскрываемые айтемы с подробным описанием
        binding.recyclerPositive.adapter = adapterPositive

        adapterNegative = SimpleAdapter(SimpleDetailsListener(::listener))
        binding.recyclerNegative.setHasFixedSize(true)
        binding.recyclerNegative.adapter = adapterNegative

        // todo может и не нужены
        toolbarAnimationWithRecyclerView(binding.recyclerPositive)
        toolbarAnimationWithRecyclerView(binding.recyclerNegative)

    }

    override fun renderState(state: SimpleDetailsViewState) {
        when (state) {
            is SimpleDetailsViewState.SuccessLists -> {
                adapterPositive?.submitList(state.positiveList)
                adapterNegative?.submitList(state.negativeList)
            }
        }
    }
}
