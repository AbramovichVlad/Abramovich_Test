package com.test.abramovich.hearthstone.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.abramovich.hearthstone.databinding.FragmentFavoriteBinding
import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.presentation.adapter.card.CardsAdapter
import com.test.abramovich.hearthstone.presentation.model.CardUiModel
import com.test.abramovich.hearthstone.presentation.model.TabMain
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseListFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val adapter by lazy {
        CardsAdapter(
            clickFavorite = {
                mainViewModel.changeCardFavorite(it)
            },
            clickCard = {
                navigation.navigateDetails(it, TabMain.FAVORITE)
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.updateCards()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        lifecycleScope.launch {
            mainViewModel.cardListFavoriteUi.collect {
                when (it) {
                    is CardUiModel.Exception -> setStateException(it.message)
                    is CardUiModel.Loading -> setStateLoading()
                    is CardUiModel.Successfully -> setStateSuccessfully(it.cards)
                }
            }
        }
    }

    override fun setStateException(message: String?) {
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = false
        binding.textException.isVisible = true
        if (message != null) binding.textException.text = message
    }

    override fun setStateLoading() {
        binding.textException.isVisible = false
        binding.recyclerView.isVisible = false
        binding.progressBar.isVisible = true
    }

    override fun setStateSuccessfully(cards: List<Card>) {
        binding.textException.isVisible = false
        binding.progressBar.isVisible = false
        binding.recyclerView.isVisible = true
        adapter.submitList(cards)
    }

    private fun initRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}