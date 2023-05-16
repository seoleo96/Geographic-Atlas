package com.example.geographicatlas.ui.adapter.countrieslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geographicatlas.databinding.CountryItemBinding
import com.example.geographicatlas.ui.adapter.AdapterDelegate
import com.example.geographicatlas.ui.adapter.DelegateItem
import com.example.geographicatlas.ui.base.FragmentUtils

class CountriesAdapter(
    private val onClickItem: (CountriesListAdapterItem) -> Unit,
) : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup) = PeopleViewHolder(
        CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, item: DelegateItem, position: Int) {
        (viewHolder as PeopleViewHolder).bind(item.content() as CountriesListAdapterItem)
    }

    override fun isOfViewType(item: DelegateItem) = item is CountriesListAdapterItem

    override fun onBindViewHolder(holder: ViewHolder, item: DelegateItem, payloads: List<Any>) {}

    inner class PeopleViewHolder(
        private val binding: CountryItemBinding,
    ) : ViewHolder(binding.root) {

        fun bind(item: CountriesListAdapterItem) {
            binding.apply {
                FragmentUtils.loadImage(item.flag, countryIcon)
                countryName.text = item.name
                countryCapital.text = item.capital
                populationValue.text = item.population.toString()
                areaValue.text = item.area.toString()
                currenciesValue.text = item.currency
            }
        }
    }
}