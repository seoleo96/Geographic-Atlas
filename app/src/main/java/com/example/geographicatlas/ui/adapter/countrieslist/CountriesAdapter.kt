package com.example.geographicatlas.ui.adapter.countrieslist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.CountryItemBinding
import com.example.geographicatlas.ui.adapter.AdapterDelegate
import com.example.geographicatlas.ui.adapter.DelegateItem
import com.example.geographicatlas.ui.base.FragmentUtils

class CountriesAdapter(
    private val onClickExpandIcon: (CountriesListAdapterItem) -> Unit,
    private val onClickLearnMore: (CountriesListAdapterItem) -> Unit,
) : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup) = CountriesViewHolder(
        CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(viewHolder: ViewHolder, item: DelegateItem, position: Int) {
        (viewHolder as CountriesViewHolder).bind(item.content() as CountriesListAdapterItem)
    }

    override fun isOfViewType(item: DelegateItem) = item is CountriesListAdapterItem

    override fun onBindViewHolder(holder: ViewHolder, item: DelegateItem, payloads: List<Any>) {
        (holder as CountriesViewHolder).bind(item.content() as CountriesListAdapterItem)
    }

    inner class CountriesViewHolder(
        private val binding: CountryItemBinding,
    ) : ViewHolder(binding.root) {

        fun bind(item: CountriesListAdapterItem) {
            binding.apply {
                FragmentUtils.loadImage(item.flag, countryIcon)
                countryName.text = item.name
                countryCapital.text = item.capital
                val populationText = "${item.population} ${this.populationValue.context.resources.getString(R.string.mln)}"
                populationValue.text = populationText
                areaValue.text = item.area.toString()
                currenciesValue.text = item.currency
                binding.countryInfoWrapper.isVisible = item.isExpand
                setArrowDrawable(item.isExpand)
                binding.expand.setOnClickListener {
                    onClickExpandIcon.invoke(item)
                }
                binding.learnMore.setOnClickListener {
                    onClickLearnMore.invoke(item)
                }
            }
        }

        private fun setArrowDrawable(isExpand: Boolean) {
            val drawable = if (isExpand) {
                R.drawable.ic_up
            } else {
                R.drawable.ic_down
            }
            binding.expand.setImageDrawable(
                ContextCompat.getDrawable(binding.expand.context, drawable)
            )
        }
    }
}