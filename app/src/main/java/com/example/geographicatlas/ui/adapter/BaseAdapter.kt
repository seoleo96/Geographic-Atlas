package com.example.geographicatlas.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter : ListAdapter<DelegateItem, RecyclerView.ViewHolder>(DelegateItemDiffUtil()) {

    private val delegates = mutableListOf<AdapterDelegate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegates[viewType].onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position), position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val delegateAdapter: AdapterDelegate = delegates[getItemViewType(position)]
            val delegatePayloads = payloads.map { it as DelegateItem.Payloadable }
            delegateAdapter.onBindViewHolder(holder, getItem(position), delegatePayloads)
        }
    }

    fun addDelegate(delegate: AdapterDelegate) {
        delegates.add(delegate)
    }

    override fun getItemViewType(position: Int): Int {
        return delegates.indexOfFirst { it.isOfViewType(currentList[position]) }
    }
}