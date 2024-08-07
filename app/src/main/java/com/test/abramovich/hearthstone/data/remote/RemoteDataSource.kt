package com.test.abramovich.hearthstone.data.remote

import com.test.abramovich.hearthstone.data.remote.model.AllCardsApiModel
import com.test.abramovich.hearthstone.data.remote.model.CardApiModel

interface RemoteDataSource {

    suspend fun getAllCards() : List<CardApiModel>?
}