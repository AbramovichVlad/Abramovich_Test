package com.test.abramovich.hearthstone.data.remote

import android.util.Log
import com.test.abramovich.hearthstone.data.remote.model.AllCardsApiModel
import com.test.abramovich.hearthstone.data.remote.model.CardApiModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor() : RemoteDataSource {

    private val retrofit by lazy {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(ConstantApi.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(ConstantApi.WRITE_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(ConstantApi.READ_TIME_OUT, TimeUnit.SECONDS)
        val client = builder.build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ConstantApi.BASE_URL)
            .client(client)
            .build()
    }

    private val api: HearthstoneRequest by lazy {
        retrofit.create(HearthstoneRequest::class.java)
    }

    override suspend fun getAllCards(): List<CardApiModel>? {
        return try {
            api.getAllCard()
        } catch (exception: Exception) {
            null
        }
    }


}