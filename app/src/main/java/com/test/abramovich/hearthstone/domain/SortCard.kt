package com.test.abramovich.hearthstone.domain

import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.domain.model.SortDirection
import com.test.abramovich.hearthstone.domain.model.SortField
import com.test.abramovich.hearthstone.domain.model.SortType
import java.text.Collator
import java.util.Locale
import javax.inject.Inject

class SortCard @Inject constructor() {

    fun sort(cards: List<Card>, sortType: SortType): List<Card> {
        return when (sortType.field) {
            SortField.COAST -> sortCoast(cards, sortType.direction)
            SortField.NAME -> sortName(cards, sortType.direction)
        }
    }

    private fun sortCoast(cards: List<Card>, direction: SortDirection): List<Card> {
        return when (direction) {
            SortDirection.ASCENDING -> cards.sortedBy { it.coast }
            SortDirection.DESCENDING -> cards.sortedByDescending { it.coast }
        }
    }

    private fun sortName(cards: List<Card>, direction: SortDirection): List<Card> {
        val collator = getCollator()
        return when (direction) {
            SortDirection.ASCENDING -> cards.sortedWith { s1, s2 -> collator.compare(s1.name, s2.name) }
            SortDirection.DESCENDING -> cards.sortedWith{ s1, s2 -> collator.compare(s2.name, s1.name) }
        }
    }

    private fun getCollator() = Collator.getInstance(Locale("ru", "RU"))
}