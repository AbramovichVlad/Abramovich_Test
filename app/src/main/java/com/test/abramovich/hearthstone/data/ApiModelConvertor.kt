package com.test.abramovich.hearthstone.data

import android.text.Html
import com.test.abramovich.hearthstone.data.remote.model.CardApiModel
import com.test.abramovich.hearthstone.domain.model.Card
import javax.inject.Inject

class ApiModelConvertor @Inject constructor() {

    suspend fun apiModelToCard(list: List<CardApiModel>): HashMap<String, Card> {
        val result = hashMapOf<String, Card>()
        list.map { apiCard ->
            val idCard = apiCard.cardId ?: "0"
            result.put(
                idCard, Card(
                    idCard,
                    apiCard.name,
                    apiCard.playerClass,
                    apiCard.cost,
                    getDescription(apiCard.description),
                    apiCard.image,
                    false
                )
            )
        }
        return result
    }

    private fun getDescription(description: String?): String? {
        return if (description != null) Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY)
            .toString()
        else null
    }
}