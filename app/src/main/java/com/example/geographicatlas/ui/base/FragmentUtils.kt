package com.example.geographicatlas.ui.base


import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.geographicatlas.ui.adapter.DelegateItem
import com.example.geographicatlas.ui.adapter.continent.ContinentAdapterItem
import com.example.geographicatlas.ui.adapter.countrieslist.CountriesListAdapterItem


object FragmentUtils {

    fun loadImage(url: String?, view: ImageView) = Glide.with(view.context).load(url).into(view)

    fun List<CountriesListAdapterItem>.toWithContinents(): List<DelegateItem> {
        val delegateItemList = mutableListOf<DelegateItem>()
        var lastContinent = ""
        this
            .sortedBy { it.continent }
            .forEach { country ->
                if (lastContinent != country.continent) {
                    delegateItemList.add(
                        ContinentAdapterItem(
                            id = country.id,
                            continent = country.continent
                        )
                    )
                    lastContinent = country.continent
                }
                delegateItemList.add(country)
            }
        return delegateItemList
    }

    fun showToast(msg: String, context: Context) {
        Toast
            .makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            )
            .show()
    }

}