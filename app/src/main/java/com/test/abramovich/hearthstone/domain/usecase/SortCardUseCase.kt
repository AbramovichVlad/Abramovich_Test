package com.test.abramovich.hearthstone.domain.usecase

import com.test.abramovich.hearthstone.domain.Repository
import com.test.abramovich.hearthstone.domain.SortCard
import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.domain.model.SortType
import javax.inject.Inject

class SortCardUseCase @Inject constructor(private val sortCard: SortCard) {

    suspend fun execute(cards: List<Card>, sortType: SortType) = sortCard.sort(cards, sortType)

}
