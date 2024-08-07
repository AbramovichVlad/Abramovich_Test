package com.test.abramovich.hearthstone.presentation.activity

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.test.abramovich.hearthstone.R
import com.test.abramovich.hearthstone.databinding.ActivityMainBinding
import com.test.abramovich.hearthstone.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavHost()
        initBackStack()
    }

    private fun initNavHost() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navigation.addNavController(navController)
    }

    private fun initBackStack() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (findNavController(R.id.nav_host).currentBackStackEntry != null) {
                    findNavController(R.id.nav_host).popBackStack()
                } else {
                    finish()
                }
            }
        })
    }

}