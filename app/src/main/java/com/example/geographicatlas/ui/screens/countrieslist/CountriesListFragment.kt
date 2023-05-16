package com.example.geographicatlas.ui.screens.countrieslist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
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
import com.example.geographicatlas.ui.adapter.countrieslist.CountyDividerItemDecoration
import com.example.geographicatlas.ui.base.FragmentUtils.showToast
import com.seoleo.zulipmessenger.ui.base.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private val binding by viewBinding(FragmentCountriesListBinding::bind)
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { BaseAdapter() }
    private val viewModel: CountriesListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        setUpAdapter()
        observeViewState()
    }

    private fun observeViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { observeCountries() }
                launch { observeLoadingState() }
                launch { observeErrorState() }
            }
        }
    }

    private suspend fun observeErrorState() {
        viewModel.errorState().collect { throwable ->
            throwable?.let { error ->
                val message = when (error) {
                    is UnknownHostException -> {
                        getString(R.string.no_internet_connection)
                    }
                    else -> {
                        getString(R.string.smth_went_wrong_error)
                    }
                }
                showToast(message, requireContext())
                viewModel.resetErrorState()
            }
        }
    }

    private suspend fun observeCountries() {
        viewModel.countries().collect { countries ->
            adapter.submitList(countries)
        }
    }

    private suspend fun observeLoadingState() {
        viewModel.loadingState().collect { isLoading ->
            if (isLoading) {
                binding.recycler.isVisible = false
                binding.shimmerView.apply {
                    shimmerViewContainer.startShimmer()
                    root.isVisible = true
                }
            } else {
                binding.shimmerView.apply {
                    shimmerViewContainer.stopShimmer()
                    root.isVisible = false
                }
                binding.recycler.isVisible = true
            }
        }
    }

    private fun setUpAdapter() {
        adapter.addDelegate(CountriesAdapter(::onClickExpandIcon, ::onClickLearnMore))
        adapter.addDelegate(ContinentAdapter())
        binding.recycler.addItemDecoration(CountyDividerItemDecoration())
        binding.recycler.adapter = adapter
    }

    private fun onClickExpandIcon(item: CountriesListAdapterItem) {
        viewModel.updateCountry(item.copy(isExpand = !item.isExpand))
    }

    private fun onClickLearnMore(item: CountriesListAdapterItem) {
        val action = CountriesListFragmentDirections.actionCountriesListFragmentToCountryDetailsFragment(item.cca2)
        findNavController().navigate(action)
    }

    private fun setUpToolbar() {
        binding.toolbar.screenName.text = getString(R.string.countries_list_screen_name)
    }

    companion object {
        private const val TAG = "CountriesActions"
    }
}