package com.test.abramovich.hearthstone.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<ViewBindingType : ViewBinding>(
    private val bindingFactory: (LayoutInflater) -> ViewBindingType,
    context: Context,
    theme: Int = android.R.style.Theme_Translucent_NoTitleBar_Fullscreen
) : Dialog(context, theme), LifecycleOwner {

    private var _binding: ViewBindingType? = null

    protected val binding
        get() = requireNotNull(_binding)

    private val lifecycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
        onCreate()
    }

    abstract fun onCreate()

    override fun cancel() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        _binding = null
        super.cancel()
    }

    override fun dismiss() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        _binding = null
        super.dismiss()
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry
}