package com.example.geographicatlas.ui.adapter.continent

import com.example.geographicatlas.ui.adapter.DelegateItem

data class ContinentAdapterItem(
    val id: Int,
    val continent: String,
) : DelegateItem {

    override fun content(): Any = this

    override fun id(): Int = this.id

    override fun compareToOther(other: DelegateItem) = (other as ContinentAdapterItem) == this

    override fun payload(other: DelegateItem): DelegateItem.Payloadable {
        return DelegateItem.Payloadable.None
    }
}