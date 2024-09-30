package com.example.geographicatlas.ui.screens.countrieslist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.geographicatlas.R
import com.example.geographicatlas.data.cloud.RetrofitInstance
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import com.example.geographicatlas.ui.adapter.BaseAdapter
import com.example.geographicatlas.ui.base.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private val binding by viewBinding(FragmentCountriesListBinding::bind)
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { BaseAdapter() }
    private val viewModel: CountriesListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()

        binding.xReport.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyAic2hpZnRJRCI6ICIxIiwgImRlcGFydG1lbnROYW1lIjoic3RyaW5nIiwgImRlcGFydG1lbnRDb2RlIjogInN0cmluZyIgfQ%3D%3D&sign=ZDNhNDRlMzg0N2YzNzBkZTViMGE0Mzc0MGE1Y2U5N2E0ZGY5MDNjZQ%3D%3D"
                val response = RetrofitInstance().serviceTest().xReport(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.getInfo.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyJlbXBsb3llZU5hbWUiOiLQmtCw0YHRgdC40YAifQ%3D%3D&sign=N2RiYjA1NDU1ZDNkZGFhNmE3OGU3OTU5Y2YxYmZiOTg0MzA3NTVmNw%3D%3D"
                val response = RetrofitInstance().serviceTest().getInfo(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.sale.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body1 = "data=eyAgICAgImRvY051bWJlciI6ICJUSzAwMDMwNDc0MDMiLCAgICAgIndzTmFtZSI6ICIgIiwgICAgICJkZXBhcnRtZW50TmFtZSI6ICIgIiwgICAgICJlbXBsb3llZU5hbWUiOiAiT2dheSBWaWt0b3JpeWEiLCAgICAgImFtb3VudCI6IDIyMDAwMDAsICAgICAiY3VycmVuY3kiOiAi0YHRg9C8IiwgICAgICJpdGVtcyI6IFsgICAgICAgICB7ICAgICAgICAgICAgICJpdGVtSWQiOiAiICIsICAgICAgICAgICAgICJpdGVtTmFtZSI6ICI4OTA5MTE2NTcxKEJpbGxpbmdnYSB0bydsb3YpIiwgICAgICAgICAgICAgIml0ZW1BdHRyIjogMSwgICAgICAgICAgICAgIml0ZW1Db2RlIjogIjEwMzA0MDA4MDA2MDAwMDAwIiwgICAgICAgICAgICAgIml0ZW1RdHkiOiAxMDAwLCAgICAgICAgICAgICAiaXRlbUFtb3VudCI6IDIyMDAwMDAsICAgICAgICAgICAgICJkaXNjb3VudCI6IDAsICAgICAgICAgICAgICJpdGVtVGF4ZXMiOiBbICAgICAgICAgICAgICAgICB7ICAgICAgICAgICAgICAgICAgICAgInRheE5hbWUiOiAic2h1IGp1bWxhZGFuIFFRUzoiLCAgICAgICAgICAgICAgICAgICAgICJ0YXhQcmMiOiAwICAgICAgICAgICAgICAgICB9ICAgICAgICAgICAgIF0sICAgICAgICAgICAgICJpdGVtTWFyZ2luU3VtIjogMCwgICAgICAgICAgICAgIml0ZW1NYXJnaW5QcmljZSI6IDAgICAgICAgICB9ICAgICBdLCAgICAgInBheW1lbnRzIjogeyAgICAgICAgICJjYXNoQW1vdW50IjogMjIwMDAwMCwgICAgICAgICAiY2FzaGxlc3NBbW91bnQiOiAwLCAgICAgICAgICJjcmVkaXRBbW91bnQiOiAwLCAgICAgICAgICJib251c2VzQW1vdW50IjogMCwgICAgICAgICAicHJlcGF5bWVudEFtb3VudCI6IDAgICAgIH0sICAgICAiZmlzY2FsSUQiOiAiIiwgICAgICJwcmludEZvb3RlciI6ICIgIiB9&sign=NjMzYzMxNWIxMTRkMWRmYWY2YzZlZDA2NTE5ZDQ2OWU1ZjJiY2MxZg%3D%3D"
                val response = RetrofitInstance().serviceTest().sale(body1)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.checkShift.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyJlbXBsb3llZU5hbWUiOiLQkCJ9&sign=YzAwZmZmN2YxYTJkYjE0Zjc4MGFkODU4N2NhNGRlN2FlYmQ1NWQ2Mw%3D%3D"
                val response = RetrofitInstance().serviceTest().checkShift(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.close.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyJlbXBsb3llZU5hbWUiOiLQmtCw0YHRgdC40YAiLCJkZXBhcnRtZW50TmFtZSI6IiDQnNCw0LPQsNC30LjQvSJ9&sign=NmEwYjhhN2Q1MGMzODAxMjE2ZTQxZTQ0NjgzNmVjMTQyMjk0OGJiZQ%3D%3D"
                val response = RetrofitInstance().serviceTest().closeShift(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.open.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyJlbXBsb3llZU5hbWUiOiLQmtCw0YHRgdC40YAiLCJwaW5jb2RlIjoxMTExMTF9&sign=YzRmMDcwNjE5ZDU5NGVlMGQzNjk2ODhlYTZiY2U2ZTM0MjkyOWZhZA%3D%3D"
                val response = RetrofitInstance().serviceTest().closeShift(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.open.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyJlbXBsb3llZU5hbWUiOiLQmtCw0YHRgdC40YAiLCJwaW5jb2RlIjoxMTExMTF9&sign=YzRmMDcwNjE5ZDU5NGVlMGQzNjk2ODhlYTZiY2U2ZTM0MjkyOWZhZA%3D%3D"
                val response = RetrofitInstance().serviceTest().openShift(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.deposit.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyAiY2FzaCI6IDEwMDAwIH0%3D&sign=MDIxNzc3ODdmMTE1ZGVkMzViMWE2MjNjMDc0NjA3MzdiYzhkMmNkYQ%3D%3D"
                val response = RetrofitInstance().serviceTest().deposit(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }

        binding.withdraw.setOnClickListener {
            binding.response.text = PROGRESS_TEXT
            lifecycleScope.launch(Dispatchers.IO){
                val body = "data=eyAiY2FzaCI6IDEwMDAwIH0%3D&sign=MDIxNzc3ODdmMTE1ZGVkMzViMWE2MjNjMDc0NjA3MzdiYzhkMmNkYQ%3D%3D"
                val response = RetrofitInstance().serviceTest().withdraw(body)
                Log.d(TAG, "onCreate: respone $response")
                Log.d(TAG, "onCreate: ${response.body()}")
                withContext(Dispatchers.Main){
                    binding.response.text = response.body()
                }
            }
        }
    }

    private fun setUpToolbar() {
        binding.toolbar.screenName.text = "Запросы"
    }

    companion object {
        private const val TAG = "CountriesListFragment"
        private const val PROGRESS_TEXT = "sending query..."
    }
}