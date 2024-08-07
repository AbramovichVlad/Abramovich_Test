package com.test.abramovich.hearthstone.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.domain.model.SortType
import com.test.abramovich.hearthstone.domain.usecase.AddFavoriteUseCase
import com.test.abramovich.hearthstone.domain.usecase.DeleteFavoriteUseCase
import com.test.abramovich.hearthstone.domain.usecase.GetCardsUseCase
import com.test.abramovich.hearthstone.domain.usecase.SearchCardUseCase
import com.test.abramovich.hearthstone.domain.usecase.SortCardUseCase
import com.test.abramovich.hearthstone.presentation.model.CardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val sortUseCase: SortCardUseCase,
    private val searchCardUseCase: SearchCardUseCase,
) : ViewModel() {

    private val cardList = MutableStateFlow<List<Card>?>(emptyList())
    private val _cardListUi = MutableStateFlow<CardUiModel>(CardUiModel.Loading())
    val cardListUi: StateFlow<CardUiModel> = _cardListUi
    val cardListFavoriteUi: StateFlow<CardUiModel> = _cardListUi
        .map { uiModel ->
            when (uiModel) {
                is CardUiModel.Successfully -> CardUiModel.Successfully(uiModel.cards.filter { it.isFavorite })
                else -> uiModel
            }
        }
        .stateIn(
            scope = CoroutineScope(Dispatchers.IO),
            started = SharingStarted.Lazily,
            initialValue = CardUiModel.Loading()
        )

    private var sortType = SortType()

    fun handleUpdateCards(){
        viewModelScope.launch {
            _cardListUi.emit(CardUiModel.Loading())
            cardList.value = getCardsUseCase.execute()?.let { sortUseCase.execute(it, sortType) }
            if (cardList.value != null) cardList.value?.let {
                _cardListUi.emit(
                    CardUiModel.Successfully(
                        it
                    )
                )
            }
            else _cardListUi.emit(CardUiModel.Exception())
        }
    }

    fun updateCards() {
        viewModelScope.launch {
            if (cardList.value.isNullOrEmpty()) handleUpdateCards()
        }
    }

    fun search(searPattern: String) {
        viewModelScope.launch {
            val sortCard = cardList.value?.let { searchCardUseCase.execute(it, searPattern) }
            if (sortCard.isNullOrEmpty()) _cardListUi.emit(CardUiModel.Exception("Not found"))
            else _cardListUi.emit(CardUiModel.Successfully(sortCard))
        }
    }


    fun changeCardFavorite(card: Card) {
        viewModelScope.launch {
            updateFavoriteInGeneralList(card)
            updateFavoriteLocal(card)
        }
    }

    private suspend fun updateFavoriteInGeneralList(newCard: Card) {
        val updatedItems = cardList.value?.map { card ->
            if (card.id == newCard.id) {
                card.copy(isFavorite = !newCard.isFavorite)
            } else {
                card
            }
        }
        updatedItems?.let { cardList.value = it }
        updatedItems?.let { _cardListUi.emit(CardUiModel.Successfully(it)) }

    }

    private suspend fun updateFavoriteLocal(card: Card) {
        if (card.isFavorite) deleteFavoriteUseCase.execute(card)
        else addFavoriteUseCase.execute(card)
    }

    fun getSortType() = sortType

    fun setSortType(sortType: SortType) {
        viewModelScope.launch {
            this@MainViewModel.sortType = sortType
            val sortList = cardList.value?.let {sortUseCase.execute(it, sortType)}
            sortList?.let {  cardList.value = it }
            sortList?.let { _cardListUi.emit(CardUiModel.Successfully(it)) }
            Log.d("tagDataSot", "1${sortList?.get(0)?.name}1")
            Log.d("tagDataSot", "1${sortList?.get(1)?.name}1")
        }
    }

}