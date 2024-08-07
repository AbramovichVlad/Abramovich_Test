package com.test.abramovich.hearthstone.presentation.adapter.card

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.abramovich.hearthstone.R
import com.test.abramovich.hearthstone.databinding.CardItemBinding
import com.test.abramovich.hearthstone.domain.model.Card
import com.test.abramovich.hearthstone.presentation.utils.getBinding
import com.test.abramovich.hearthstone.presentation.utils.setFavorite

class CardsAdapter(
    private val clickFavorite: (Card) -> Unit,
    private val clickCard: (Int) -> Unit
) :
    ListAdapter<Card, CardViewHolder>(CardDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(parent.getBinding(CardItemBinding::inflate), clickFavorite, clickCard)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun onBindViewHolder(
        holder: CardViewHolder,
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

class CardViewHolder(
    private val binding: CardItemBinding,
    private val clickFavorite: (Card) -> Unit,
    private val clickCard: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(card: Card, position: Int) {
        val context = binding.root.context
        setImage(context, card.image)
        setText(context, card)
        setFavorite(card)
        binding.root.setOnClickListener {
            clickCard.invoke(position)
        }
    }

    fun setFavorite(card: Card) {
        binding.btnFavorite.setFavorite(card.isFavorite)
        binding.btnFavorite.setOnClickListener {
            clickFavorite.invoke(card)
        }
    }

    private fun setText(context: Context, card: Card) {
        binding.textName.text = context.getString(R.string.name_placeholder, card.name)
        binding.textClass.text = context.getString(R.string.class_placeholder, card.className)

        binding.textCoast.isVisible = card.coast != null
        binding.textCoast.text =
            context.getString(R.string.coast_placeholder, card.coast.toString())

        binding.textDesc.isVisible = card.description != null
        binding.textDesc.text =
            context.getString(R.string.description_placeholder, card.description)
    }

    private fun setImage(context: Context, url: String?) {
        Glide.with(context).load(url).placeholder(R.drawable.ic_image_not_found)
            .into(binding.image)
    }

}