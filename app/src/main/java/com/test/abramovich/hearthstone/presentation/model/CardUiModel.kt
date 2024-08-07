package com.test.abramovich.hearthstone.presentation.model

import com.test.abramovich.hearthstone.domain.model.Card

sealed class CardUiModel {
    class Exception(val message : String? = null) : CardUiModel()
    class Loading : CardUiModel()
    class Successfully(val cards : List<Card>) : CardUiModel()
}