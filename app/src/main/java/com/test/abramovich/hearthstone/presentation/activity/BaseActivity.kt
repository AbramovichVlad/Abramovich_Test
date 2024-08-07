package com.test.abramovich.hearthstone.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.test.abramovich.hearthstone.presentation.naviagtion.Navigation
import javax.inject.Inject

abstract class BaseActivity<ViewBind : ViewBinding>(val bindingFactory: (LayoutInflater) -> ViewBind) :
    AppCompatActivity() {

    @Inject
    lateinit var navigation: Navigation

    val binding get() = _binding!!
    private var _binding: ViewBind? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(_binding?.root)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}