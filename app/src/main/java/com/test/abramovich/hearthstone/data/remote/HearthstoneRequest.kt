package com.test.abramovich.hearthstone.data.remote

import com.test.abramovich.hearthstone.BuildConfig
import com.test.abramovich.hearthstone.data.remote.model.AllCardsApiModel
import com.test.abramovich.hearthstone.data.remote.model.CardApiModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface HearthstoneRequest {

    @Headers(
        ConstantApi.HEADER_ACCEPT_JSON,
        "${ConstantApi.HEADER_KEY}: ${BuildConfig.API_KEY}",
        "${ConstantApi.HEADER_HOST_NAME}: ${ConstantApi.HEADER_HOST_VALUE}"
    )
    @GET("cards/classes/{className}")
    suspend fun getAllCard(
        @Path("className") classes : String = "Hunter",
        @Query("locale") locale : String = "ruRU",
        @Query("collectible") collectible : Int = 1
    ) : List<CardApiModel>?


}