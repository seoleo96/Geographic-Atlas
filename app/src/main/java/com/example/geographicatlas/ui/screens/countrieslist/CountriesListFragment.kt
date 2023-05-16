package com.example.geographicatlas.ui.screens.countrieslist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import com.example.geographicatlas.ui.adapter.BaseAdapter
import com.example.geographicatlas.ui.adapter.continent.ContinentAdapter
import com.example.geographicatlas.ui.adapter.countrieslist.CountriesAdapter
import com.example.geographicatlas.ui.adapter.countrieslist.CountriesListAdapterItem
import com.seoleo.zulipmessenger.ui.base.viewBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private val binding by viewBinding(FragmentCountriesListBinding::bind)
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { BaseAdapter() }

    //    private lateinit var adapter: FilmsAdapter
    private val viewModel: CountriesListViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchCountries()

        setUpToolbar()
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_countriesListFragment_to_countryDetailsFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countries.collect {
                    Log.e(TAG, "onViewCreated: ${it.size}")
                    adapter.submitList(it)
                }
            }
        }

        setUpAdapter()
    }

    private fun setUpAdapter() {
        adapter.addDelegate(CountriesAdapter(::onClickItem))
        adapter.addDelegate(ContinentAdapter())
        binding.recycler.addItemDecoration(CountyDividerItemDecoration())
        binding.recycler.adapter = adapter
    }

    private fun onClickItem(item: CountriesListAdapterItem) {

    }

    private fun setUpToolbar() {
        binding.toolbar.screenName.text = getString(R.string.countries_list_screen_name)
    }

    companion object {
        private const val TAG = "CountriesListFragment"
    }
}