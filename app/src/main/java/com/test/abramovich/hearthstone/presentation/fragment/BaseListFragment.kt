package com.test.abramovich.hearthstone.presentation.fragment

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.test.abramovich.hearthstone.domain.model.Card

abstract class BaseListFragment<T : ViewBinding>(bindingFactory: (LayoutInflater) -> T) :
    BaseFragment<T>(bindingFactory) {

       protected abstract fun setStateException(message : String? = null)

      protected  abstract fun setStateLoading()

       protected abstract fun setStateSuccessfully(cards: List<Card>)
    }