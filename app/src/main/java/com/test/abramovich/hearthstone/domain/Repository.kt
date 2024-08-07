package com.test.abramovich.hearthstone.domain

import com.test.abramovich.hearthstone.domain.model.Card


interface Repository {

   suspend fun getAllCards() : List<Card>?

   suspend fun getFavoriteCards() : List<Card>

   suspend fun addFavoriteCard(card: Card)

   suspend fun deleteFavoriteCard(card: Card)
}