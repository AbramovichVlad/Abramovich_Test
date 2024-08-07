package com.test.abramovich.hearthstone.data

import com.test.abramovich.hearthstone.data.local.FavoriteCardDao
import com.test.abramovich.hearthstone.data.remote.RemoteDataSource
import com.test.abramovich.hearthstone.domain.Repository
import com.test.abramovich.hearthstone.domain.model.Card
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localData: FavoriteCardDao,
    private val remoteData: RemoteDataSource,
    private val apiModelConvertor: ApiModelConvertor
) : Repository {


    override suspend fun getAllCards(): List<Card>? {
        val remoteCard = remoteData.getAllCards() ?: return null
        val cards = apiModelConvertor.apiModelToCard(remoteCard)
        return setFavoriteInAllCard(cards)
    }

    override suspend fun getFavoriteCards(): List<Card> {
        return localData.getCards()
    }


    override suspend fun addFavoriteCard(card: Card) {
        localData.insertCard(card)
    }

    override suspend fun deleteFavoriteCard(card: Card) {
        localData.deleteCardById(card.id)
    }

    private suspend fun setFavoriteInAllCard(cards: HashMap<String, Card>): List<Card> {
        val favoriteCards = getFavoriteCards()
        favoriteCards.forEach {
            cards[it.id]?.isFavorite = true
        }
        return cards.values.toList()
    }

}