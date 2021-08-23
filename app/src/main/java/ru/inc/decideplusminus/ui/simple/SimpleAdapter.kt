package ru.inc.decideplusminus.ui.simple

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.inc.decideplusminus.databinding.ItemSimpleDecideBinding
import ru.inc.decideplusminus.ui.models.SimpleDecide

class SimpleAdapter : RecyclerView.Adapter<SimpleAdapter.DecideViewHolder>() {

    var list: List<SimpleDecide> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DecideViewHolder(ItemSimpleDecideBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: DecideViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size

    inner class DecideViewHolder(val ui: ItemSimpleDecideBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bind(simpleDecide: SimpleDecide) {

        }
    }
}