package com.test.abramovich.hearthstone.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.test.abramovich.hearthstone.databinding.FragmentDetailsBinding
import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.presentation.adapter.DetailsViewPagerAdapter
import com.test.abramovich.hearthstone.presentation.model.CardUiModel
import com.test.abramovich.hearthstone.presentation.model.TabMain
import com.test.abramovich.hearthstone.presentation.naviagtion.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseListFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val cardListType by lazy {
        TabMain.valueOf(arguments?.getString(Navigation.KEY_LIST_TYPE) ?: TabMain.HOME.name)
    }

    private val adapter by lazy {
        DetailsViewPagerAdapter(
            clickFavorite = {
                mainViewModel.changeCardFavorite(it)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.updateCards()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        lifecycleScope.launch {
            val cardFlow = when(cardListType){
                TabMain.HOME -> mainViewModel.cardListUi
                TabMain.FAVORITE -> mainViewModel.cardListFavoriteUi
            }
            cardFlow.collect {
                when (it) {
                    is CardUiModel.Exception -> setStateException(it.message)
                    is CardUiModel.Loading -> setStateLoading()
                    is CardUiModel.Successfully -> setStateSuccessfully(it.cards)
                }
            }
        }
    }

    private fun initView() {
        initViewPager()

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getAdapterPosition(): Int {
        val position = arguments?.getInt(Navigation.KEY_CARD_POSITION) ?: -1
        arguments?.remove(Navigation.KEY_CARD_POSITION)
        return position

    }

    override fun setStateException(message: String?) {
        binding.viewPager.isVisible = false
        binding.progressBar.isVisible = false
        binding.containerException.isVisible = true
        binding.btnException.setOnClickListener{
            mainViewModel.handleUpdateCards()
        }
        if (message != null) binding.textException.text = message
    }

    override fun setStateLoading() {
        binding.containerException.isVisible = false
        binding.viewPager.isVisible = false
        binding.progressBar.isVisible = true
    }

    override fun setStateSuccessfully(cards: List<Card>) {
        binding.containerException.isVisible = false
        binding.progressBar.isVisible = false
        binding.viewPager.isVisible = true
        adapter.submitList(cards)
        val position = getAdapterPosition()
        if (position > 0) {
            binding.viewPager.post {
                binding.viewPager.setCurrentItem(position, true)
            }
        }
    }

    private fun initViewPager() {
        binding.viewPager.adapter = adapter
    }

}