package com.example.geographicatlas.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface AdapterDelegate {
    fun onCreateViewHolder(parent: ViewGroup): ViewHolder
    fun onBindViewHolder(viewHolder: ViewHolder, item: DelegateItem, position: Int)
    fun onBindViewHolder(holder: ViewHolder, item: DelegateItem, payloads: List<Any>)
    fun isOfViewType(item: DelegateItem): Boolean
}