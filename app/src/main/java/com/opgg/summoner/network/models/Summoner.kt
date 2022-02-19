package com.opgg.summoner.network.models

data class Summoner(
    var name: String = "",
    var level: Int = 0,
    var profileImageUrl: String = "",
    var leagues: MutableList<League> = mutableListOf(),
)