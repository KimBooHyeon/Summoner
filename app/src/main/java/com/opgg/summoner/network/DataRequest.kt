package com.opgg.summoner.network

import retrofit2.Retrofit

class DataRequest {
    companion object {
        fun api(): ApiDataInterface {
            val server: Retrofit = RetrofitClient.getInstance()
            return server.create(ApiDataInterface::class.java)
        }
    }
}