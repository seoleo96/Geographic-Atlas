package com.example.geographicatlas.ui.screens.countrieslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import com.seoleo.zulipmessenger.ui.base.viewBinding
import org.koin.android.ext.android.inject

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private val binding by viewBinding(FragmentCountriesListBinding::bind)
//    private lateinit var adapter: FilmsAdapter
    private val viewModel: CountriesListViewModel by inject()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_countriesListFragment_to_countryDetailsFragment)
        }
    }

}