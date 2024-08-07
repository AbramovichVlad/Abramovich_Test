package com.test.abramovich.hearthstone.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.test.abramovich.hearthstone.presentation.fragment.FavoriteFragment
import com.test.abramovich.hearthstone.presentation.fragment.HomeFragment
import com.test.abramovich.hearthstone.presentation.model.TabMain

class MainViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return TabMain.entries.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(TabMain.entries[position]){
            TabMain.HOME -> HomeFragment()
            TabMain.FAVORITE -> FavoriteFragment()
        }
    }
}