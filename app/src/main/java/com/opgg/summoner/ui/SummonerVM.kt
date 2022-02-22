package com.opgg.summoner.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.opgg.summoner.network.DataRequest
import com.opgg.summoner.network.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SummonerVM @Inject constructor() : BaseVM() {
    var name = MutableLiveData<String>()
    var level = MutableLiveData<Int>()
    var profileImageUrl = MutableLiveData<String>()
    var profileBackgroundImageUrl = MutableLiveData<String>()
    var leagues = MutableLiveData<MutableList<League>>().apply { value = mutableListOf() }
    var games = MutableLiveData<MutableList<Game>>().apply { value = mutableListOf() }
    var champions = MutableLiveData<MutableList<Champions>>()
    var positions = MutableLiveData<MutableList<Positions>>()
    var summary = MutableLiveData<Summary>()
    var lastMatch: Long = 0

    init {
        getSummonerInfo("Genetory")
        getMatchList("Genetory")
    }

    fun getSummonerInfo(nickname: String) {
        DataRequest.api().getSummonerInfo(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                name.value = it.summoner.name
                level.value = it.summoner.level
                profileImageUrl.value = it.summoner.profileImageUrl
                profileBackgroundImageUrl.value = it.summoner.profileBackgroundImageUrl
                leagues.value = it.summoner.leagues
            }, { t -> t.printStackTrace()})
    }

    fun getMatchList(nickname: String) {
        DataRequest.api().getSummonerGameInfo(nickname, lastMatch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                games.value = it.games
                champions.value = it.champions
                positions.value = it.positions
                summary.value = it.summary
            }, { t -> t.printStackTrace() })
    }
}