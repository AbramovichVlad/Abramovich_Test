package com.test.abramovich.hearthstone.presentation.naviagtion

import androidx.navigation.NavController
import com.test.abramovich.hearthstone.presentation.model.TabMain

interface Navigation {

    fun addNavController(navController: NavController)

    fun navigateBack()

    fun navigateToHome()
    fun navigateToFavorite()
    fun navigateDetails(position : Int, tabScreen : TabMain)

    companion object {
        const val KEY_CARD_POSITION = "KEY_CARD_POSITION"
        const val KEY_LIST_TYPE = "KEY_LIST_TYPE"
    }
}