package com.test.abramovich.hearthstone.domain.usecase

import com.test.abramovich.hearthstone.domain.SearchCard
import com.test.abramovich.hearthstone.domain.SortCard
import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.domain.model.SortType
import javax.inject.Inject

class SearchCardUseCase @Inject constructor(private val searchCard: SearchCard) {

    suspend fun execute(cards: List<Card>, searchPattern : String) = searchCard.search(cards, searchPattern)

}