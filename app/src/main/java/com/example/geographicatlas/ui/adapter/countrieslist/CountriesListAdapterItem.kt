package com.example.geographicatlas.ui.adapter.countrieslist

import com.example.geographicatlas.ui.adapter.DelegateItem

data class CountriesListAdapterItem(
    val id: Int,
    val name: String,
    val capital: String,
    val region: String,
    val population: Int,
    val area: Double,
    val flag: String,
    val isExpand: Boolean,
    val currency: String,
    val continent: String,
    val cca2: String,
) : DelegateItem {

    override fun content(): Any = this

    override fun id(): Int = this.id

    override fun compareToOther(other: DelegateItem) = (other as CountriesListAdapterItem) == this

    override fun payload(other: DelegateItem): DelegateItem.Payloadable {
        return DelegateItem.Payloadable.None
    }
}