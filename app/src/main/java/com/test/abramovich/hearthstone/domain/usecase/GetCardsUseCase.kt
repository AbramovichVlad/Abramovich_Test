package com.test.abramovich.hearthstone.domain.usecase

import com.test.abramovich.hearthstone.domain.Repository
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val repository: Repository) {

    suspend fun execute() = repository.getAllCards()

}