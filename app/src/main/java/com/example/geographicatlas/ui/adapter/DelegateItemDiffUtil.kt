package com.example.geographicatlas.ui.adapter

import androidx.recyclerview.widget.DiffUtil

class DelegateItemDiffUtil : DiffUtil.ItemCallback<DelegateItem>() {

    override fun areItemsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
        return oldItem::class == newItem::class && oldItem.id() == newItem.id()
    }

    override fun areContentsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean {
        return oldItem::class == newItem::class && oldItem.compareToOther(newItem)
    }

    override fun getChangePayload(oldItem: DelegateItem, newItem: DelegateItem): Any {
        return oldItem.payload(newItem)
    }
}