package com.example.geographicatlas.ui.adapter.continent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geographicatlas.databinding.ItemContinentBinding
import com.example.geographicatlas.ui.adapter.AdapterDelegate
import com.example.geographicatlas.ui.adapter.DelegateItem

class ContinentAdapter : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup) = ContinentViewHolder(
        ItemContinentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, item: DelegateItem, position: Int) {
        (viewHolder as ContinentViewHolder).bind(item.content() as ContinentAdapterItem)
    }

    override fun isOfViewType(item: DelegateItem) = item is ContinentAdapterItem

    override fun onBindViewHolder(holder: ViewHolder, item: DelegateItem, payloads: List<Any>) {}

    inner class ContinentViewHolder(
        private val binding: ItemContinentBinding,
    ) : ViewHolder(binding.root) {

        fun bind(item: ContinentAdapterItem) {
            binding.continent.text = item.continent
            binding.root.tag = "continent"
        }
    }
}