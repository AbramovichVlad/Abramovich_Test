package com.test.abramovich.hearthstone.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewbinding.ViewBinding
import com.test.abramovich.hearthstone.presentation.naviagtion.Navigation
import com.test.abramovich.hearthstone.presentation.viewmodel.MainViewModel
import javax.inject.Inject

abstract class BaseFragment<ViewBind : ViewBinding>(val bindingFactory: (LayoutInflater) -> ViewBind) :
    Fragment() {

    lateinit var binding: ViewBind

    @Inject
    lateinit var navigation: Navigation

    protected  val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = bindingFactory(layoutInflater)
        return binding.root
    }

}