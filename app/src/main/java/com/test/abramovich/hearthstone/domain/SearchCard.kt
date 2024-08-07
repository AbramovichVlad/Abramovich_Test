package com.test.abramovich.hearthstone.domain

import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.presentation.model.CardUiModel
import javax.inject.Inject

class SearchCard @Inject constructor(){
    fun search(cards  : List<Card>, searchPatter : String): List<Card>? {
        return cards.filter {
            it.name?.lowercase()?.contains(searchPatter.lowercase()) == true
        }
    }
}