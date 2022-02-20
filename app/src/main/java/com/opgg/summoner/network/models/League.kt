package com.opgg.summoner.network.models

data class League(
    var wins: Int = 0,
    var losses: Int = 0,
    var tierRank: TierRank = TierRank("", "")
)

data class TierRank (
    var name: String = "",
    var tier: String = "",
    var lp: Int = 0,
    var imageUrl: String = "",
)