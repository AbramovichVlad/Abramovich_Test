package com.test.abramovich.hearthstone.data.remote.model


data class AllCardsApiModel(
  val data: Map<String, List<CardApiModel>>
)