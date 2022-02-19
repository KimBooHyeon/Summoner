package com.opgg.summoner.ui

import com.opgg.summoner.network.DataRequest
import com.opgg.summoner.network.models.League
import com.opgg.summoner.network.models.Summoner
import io.reactivex.rxjava3.core.Observable

class SummonerVM: BaseViewModel() {
    var name: String = ""
    var level: Int = 0
    var profileImageUrl: String = ""
    var leagues: MutableList<League> = mutableListOf()

    init {
        name = "Generate"
        level = 111
        profileImageUrl = "https://opgg-static.akamaized.net/images/profile_icons/profileIcon1625.jpg"
    }

    fun getSummonerInfo(nickname: String): Observable<Summoner> =
        DataRequest.api().getSummonerInfo(nickname)
}