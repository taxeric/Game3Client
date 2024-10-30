package com.lanier.game3.client.net

import com.lanier.game3.client.net.api.Game3API
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Desc:
 * Author:  幻弦让叶
 * Date:    2024/10/30 22:32
 */
object APIRequester {

    lateinit var game3Api: Game3API

    fun initAPI() {
        game3Api = Retrofit.Builder()
            .baseUrl("http://192.168.31.80:8080/")
            .callFactory(okhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Game3API::class.java)
    }

    private fun okhttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()
    }
}