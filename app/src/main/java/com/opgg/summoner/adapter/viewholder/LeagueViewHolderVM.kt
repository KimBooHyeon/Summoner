package com.opgg.summoner.adapter.viewholder

import com.opgg.summoner.network.models.League
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class LeagueViewHolderVM @Inject constructor(item: League) {
    var wins: Int = item.wins
    var losses: Int = item.losses
    var name: String = item.tierRank.name
    var tier: String = item.tierRank.tier
    var lp: Int = item.tierRank.lp
    var thumbnail: String = item.tierRank.imageUrl

    fun lp(): String {
        return DecimalFormat("#,###").format(lp)
    }

    fun score(): String {
        var percentage = 0
        try {
            percentage = wins / (wins + losses) * 100
            percentage = round(percentage.toDouble()).toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "{$wins}승 {$losses}패 ({$percentage}%)"
    }
}