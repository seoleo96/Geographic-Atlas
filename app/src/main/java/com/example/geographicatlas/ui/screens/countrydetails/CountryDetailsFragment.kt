package com.example.geographicatlas.ui.screens.countrydetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountryDetailsBinding
import com.example.geographicatlas.ui.base.FragmentUtils
import com.example.geographicatlas.ui.base.FragmentUtils.showToast
import com.seoleo.zulipmessenger.ui.base.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException

class CountryDetailsFragment : Fragment(R.layout.fragment_country_details) {

    private val binding by viewBinding(FragmentCountryDetailsBinding::bind)
    private val viewModel: CountryDetailsViewModel by viewModel()
    private val args: CountryDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        viewModel.fetchCountryInfo(args.cca2)
        observeViewState()
    }

    private fun observeViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { observeCountryDetails() }
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

    private suspend fun observeCountryDetails() {
        viewModel.countryDetails().collect { countryDetails ->
            if (countryDetails == null) return@collect
            binding.apply {
                FragmentUtils.loadImage(countryDetails.flag, countryImage)
                capitalWrapper.value.text = countryDetails.capital
                capitalWrapper.title.text = getString(R.string.capital)
                capitalCoordinatesWrapper.value.text = countryDetails.latLng ?: NOT_FOUND
                capitalCoordinatesWrapper.title.text = getString(R.string.capital_coordinates)
                val populationText = "${countryDetails.population} ${getString(R.string.mln)}"
                populationWrapper.value.text = populationText
                populationWrapper.title.text = getString(R.string.population)
                areaWrapper.value.text = "${countryDetails.area}"
                areaWrapper.title.text = getString(R.string.area)
                currencyWrapper.value.text = countryDetails.currency
                currencyWrapper.title.text = getString(R.string.currencies)
                regionWrapper.value.text = countryDetails.region
                regionWrapper.title.text = getString(R.string.region)
                timezonesWrapper.value.text = countryDetails.timezones ?: NOT_FOUND
                timezonesWrapper.title.text = getString(R.string.timezones)
                toolbar.screenName.text = countryDetails.name
            }
        }
    }

    private suspend fun observeLoadingState() {
        viewModel.loadingState().collect { isLoading ->
            Log.e("TAG", "observeLoading isLoading $isLoading: ")
//            if (isLoading) {
//                binding.detailsWrapper.isVisible = false
//                binding.shimmerView.apply {
//                    shimmerViewContainer.startShimmer()
//                    root.isVisible = true
//                }
//            } else {
//                binding.shimmerView.apply {
//                    shimmerViewContainer.stopShimmer()
//                    root.isVisible = false
//                }
//                binding.detailsWrapper.isVisible = true
//            }
        }
    }

    private fun setUpToolbar() {
        binding.toolbar.apply {
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    companion object {
        private const val NOT_FOUND = "No Found!"
    }

}