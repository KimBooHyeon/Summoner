package com.opgg.summoner.network

import com.opgg.summoner.network.models.Summoner
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDataInterface {
    @GET("/summoner/{nickname}")
    fun getSummonerInfo(
        @Path("nickname") nickname: String
    ): Call<Summoner>

    @GET("/summoner/{nickname}/matches")
    fun getSummonerGameInfo(
        @Path("nickname") nickname: String,
        @Query("lastMatch") lastMatch: String,
    )
}