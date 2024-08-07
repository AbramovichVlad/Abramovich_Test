package com.test.abramovich.hearthstone.presentation.naviagtion

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.test.abramovich.hearthstone.R
import com.test.abramovich.hearthstone.presentation.model.TabMain
import javax.inject.Inject

class NavigationImp @Inject constructor() : Navigation {

    private var navController: NavController? = null


    override fun addNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }


    override fun navigateToHome() {
        navigateById(R.id.homeFragment, navOptions = navOptionClear(R.id.favoriteFragment))
    }

    override fun navigateToFavorite() {
        navigateById(R.id.favoriteFragment, navOptions = navOptionClear(R.id.favoriteFragment))
    }

    override fun navigateDetails(position: Int, tabScreen: TabMain) {
        val bundle = Bundle()
        bundle.putInt(Navigation.KEY_CARD_POSITION, position)
        bundle.putString(Navigation.KEY_LIST_TYPE, tabScreen.name)
        navigateById(R.id.detailsFragment, bundle)
    }

    private fun navOptionClear(id: Int) = NavOptions.Builder()
        .setPopUpTo(id, true)
        .build()

    private fun navigateById(id: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
        navController?.navigate(id, bundle, navOptions)
    }
}