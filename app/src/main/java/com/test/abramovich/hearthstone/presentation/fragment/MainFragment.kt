package com.test.abramovich.hearthstone.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import com.google.android.material.tabs.TabLayoutMediator
import com.test.abramovich.hearthstone.databinding.FragmentMainBinding
import com.test.abramovich.hearthstone.presentation.adapter.MainViewPagerAdapter
import com.test.abramovich.hearthstone.presentation.dialog.DialogSort
import com.test.abramovich.hearthstone.presentation.model.TabMain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSort()
        setUpTabs()
        setUpSearch()
    }

    private fun setUpSort() {
        binding.btnSort.setOnClickListener {
            DialogSort(requireContext(), mainViewModel.getSortType()) {
                mainViewModel.setSortType(it)
            }.show()
        }
    }

    private fun setUpSearch() {
        binding.editTextSearch.doOnTextChanged { text, _, _, _ ->
            mainViewModel.search(text.toString())
        }
    }

    private fun setUpTabs() {
        val adapter = MainViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(TabMain.entries[position].res)
        }.attach()
    }


}