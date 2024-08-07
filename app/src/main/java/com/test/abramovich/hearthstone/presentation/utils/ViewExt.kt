package com.test.abramovich.hearthstone.presentation.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.test.abramovich.hearthstone.R

fun <ViewBind : ViewBinding> ViewGroup.getBinding(bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBind): ViewBind {
    return bindingInflater(
        LayoutInflater.from(this.context),
        this,
        false
    )
}

fun ImageView.setFavorite(isFavorite : Boolean){
    val favoriteRes = if (isFavorite) R.drawable.ic_favorite
    else R.drawable.ic_favorite_no_fill
    val favoriteDrawable = androidx.core.content.ContextCompat.getDrawable(context, favoriteRes)
    this.setImageDrawable(favoriteDrawable)
}