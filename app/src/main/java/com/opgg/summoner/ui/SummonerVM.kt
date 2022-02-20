package com.opgg.summoner.ui

import androidx.lifecycle.MutableLiveData
import com.opgg.summoner.network.DataRequest
import com.opgg.summoner.network.models.League
import com.opgg.summoner.network.models.Summoner
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SummonerVM @Inject constructor() : BaseVM() {
    var name = MutableLiveData<String>()
    var level = MutableLiveData<Int>()
    var profileImageUrl = MutableLiveData<String>()
    var profileBackgroundImageUrl: String = ""
    var leagues: MutableList<League> = mutableListOf()

    fun getSummonerInfo(nickname: String): Observable<Summoner> =
        DataRequest.api().getSummonerInfo(nickname)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                name.value = it.summoner.name
                level.value = it.summoner.level
                profileImageUrl.value = it.summoner.profileImageUrl
                Observable.just(it.summoner)
            }
}