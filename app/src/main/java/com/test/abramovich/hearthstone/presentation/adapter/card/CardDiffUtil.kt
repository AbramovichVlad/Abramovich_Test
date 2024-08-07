package com.test.abramovich.hearthstone.presentation.adapter.card

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.test.abramovich.hearthstone.domain.model.Card

class CardDiffUtil : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldCard: Card, newCard: Card): Boolean {
        return oldCard.id == newCard.id
    }

    override fun areContentsTheSame(oldCard: Card, newCard: Card): Boolean {
        return oldCard == newCard
    }

    override fun getChangePayload(oldCard: Card, newCard: Card): Any? {
        return if (oldCard.isFavorite != newCard.isFavorite) true else null
    }
}