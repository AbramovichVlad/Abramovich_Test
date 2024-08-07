package com.test.abramovich.hearthstone.domain

import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.domain.model.SortDirection
import com.test.abramovich.hearthstone.domain.model.SortField
import com.test.abramovich.hearthstone.domain.model.SortType
import javax.inject.Inject

class SortCard @Inject constructor() {

    fun sort(cards: List<Card>, sortType: SortType): List<Card> {
       return when (sortType.field) {
            SortField.COAST -> sortCoast(cards, sortType.direction)
            SortField.CLASS -> sortClass(cards, sortType.direction)
        }
    }

    private fun sortCoast(cards: List<Card>, direction: SortDirection): List<Card> {
       return when (direction) {
            SortDirection.ASCENDING -> cards.sortedBy { it.coast }
            SortDirection.DESCENDING -> cards.sortedByDescending { it.coast }
        }
    }

    private fun sortClass(cards: List<Card>, direction: SortDirection): List<Card> {
        return when (direction) {
            SortDirection.ASCENDING -> cards.sortedBy { it.className }
            SortDirection.DESCENDING -> cards.sortedByDescending { it.className }
        }
    }
}