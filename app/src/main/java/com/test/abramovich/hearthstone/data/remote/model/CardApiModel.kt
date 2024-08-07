package com.test.abramovich.hearthstone.data.remote.model

import com.google.gson.annotations.SerializedName


data class CardApiModel(
    @SerializedName("cardId") var cardId: String? = null,
    @SerializedName("dbfId") var dbfId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cardSet") var cardSet: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("rarity") var rarity: String? = null,
    @SerializedName("cost") var cost: Int? = null,
    @SerializedName("attack") var attack: Int? = null,
    @SerializedName("health") var health: Int? = null,
    @SerializedName("text") var description : String? = null,
    @SerializedName("artist") var artist: String? = null,
    @SerializedName("race") var race: String? = null,
    @SerializedName("playerClass") var playerClass: String? = null,
    @SerializedName("img") var image: String? = null,

    )