package com.opgg.summoner.ui

import androidx.lifecycle.MutableLiveData
import com.opgg.summoner.network.DataRequest
import com.opgg.summoner.network.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SummonerVM @Inject constructor() : BaseVM() {
    var nickname = MutableLiveData<String>().apply { value = "" }

    var name = MutableLiveData<String>()
    var level = MutableLiveData<Int>()
    var profileImageUrl = MutableLiveData<String>()
    var profileBackgroundImageUrl = MutableLiveData<String>()
    var leagues = MutableLiveData<MutableList<League>>().apply { value = mutableListOf() }

    var games = MutableLiveData<MutableList<Game>>().apply { value = mutableListOf() }
    var champions = MutableLiveData<MutableList<Champions>>()
    var positions = MutableLiveData<MutableList<Positions>>()
    var summary = MutableLiveData<Summary>()

    var hasMore: Boolean = true
    var lastMatch: Long? = null

    val isLoading = MutableLiveData<Boolean>()

    fun reset() {
        isLoading.value = true
        getSummonerInfo(nickname.value!!)

        hasMore = true
        lastMatch = null
        games.value = mutableListOf()
        getMatchList(nickname.value!!).apply {
            isLoading.value = false
        }
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
                if (lastMatch == null) {
                    champions.value = it.champions
                    positions.value = it.positions
                    summary.value = it.summary
                }
                val list: MutableList<Game> = games.value!!
                it.games.forEach {
                    list.add(it)
                }
                games.value = list
                lastMatch = it.games.last().createDate
                hasMore = it.games.isNotEmpty()
            }, { t -> t.printStackTrace() })
    }
}