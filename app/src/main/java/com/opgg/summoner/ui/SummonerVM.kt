package com.opgg.summoner.ui

import android.util.Log
import com.opgg.summoner.network.DataRequest
import com.opgg.summoner.network.models.League
import com.opgg.summoner.network.models.Summoner
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@HiltViewModel
class SummonerVM @Inject constructor() : BaseVM() {
    var name: String = ""
    var level: Int = 0
    var profileImageUrl: String = ""
    var leagues: MutableList<League> = mutableListOf()

    init {
        name = "Generate"
        level = 111
        profileImageUrl =
            "https://opgg-static.akamaized.net/images/profile_icons/profileIcon1625.jpg"
    }

    fun getSummonerInfo(nickname: String): Observable<Summoner> =
        DataRequest.api().getSummonerInfo(nickname)
}