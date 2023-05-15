package com.example.geographicatlas.ui.screens.countrydetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountryDetailsBinding
import com.seoleo.zulipmessenger.ui.base.viewBinding
import org.koin.android.ext.android.inject

class CountryDetailsFragment : Fragment(R.layout.fragment_country_details) {

    private val binding by viewBinding(FragmentCountryDetailsBinding::bind)

    //    private lateinit var adapter: FilmsAdapter
    private val viewModel: CountryDetailsViewModel by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}