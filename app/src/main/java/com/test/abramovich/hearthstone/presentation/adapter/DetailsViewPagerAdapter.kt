package com.test.abramovich.hearthstone.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.abramovich.hearthstone.R
import com.test.abramovich.hearthstone.databinding.CardDetailsItemBinding
import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.presentation.utils.getBinding
import com.test.abramovich.hearthstone.presentation.adapter.card.CardDiffUtil
import com.test.abramovich.hearthstone.presentation.utils.setFavorite

class DetailsViewPagerAdapter(
    private val clickFavorite: (Card) -> Unit,
) : ListAdapter<Card, CardDetailsViewHolder>(CardDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDetailsViewHolder {
        return CardDetailsViewHolder(parent.getBinding(CardDetailsItemBinding::inflate), clickFavorite)
    }

    override fun onBindViewHolder(holder: CardDetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(
        holder: CardDetailsViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            if (payloads[0] == true) {
                holder.setFavorite(getItem(position))
            }
        }
    }

}

class CardDetailsViewHolder(
    private val binding: CardDetailsItemBinding,
    private val clickFavorite: (Card) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(card: Card) {
        val context = binding.root.context
        setImage(context, card.image)
        setText(context, card)
        setFavorite(card)
    }

    fun setFavorite(card: Card) {
        binding.btnFavorite.setFavorite(card.isFavorite)
        binding.btnFavorite.setOnClickListener {
            clickFavorite.invoke(card)
        }
    }

    private fun setText(context: Context, card: Card) {
        binding.textName.text = context.getString(R.string.name_placeholder, card.name)
        binding.textDesc.isVisible = card.description != null
        binding.textDesc.text = context.getString(R.string.description_placeholder, card.description)
    }

    private fun setImage(context: Context, url: String?) {
        Glide.with(context).load(url).placeholder(R.drawable.ic_image_not_found)
            .into(binding.image)
    }

}