package com.test.abramovich.hearthstone.domain.usecase

import com.test.abramovich.hearthstone.domain.Repository
import com.test.abramovich.hearthstone.domain.model.Card
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute() = repository.getFavoriteCards()

}