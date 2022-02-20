package com.opgg.summoner.network.models

data class Summoner(
    var name: String = "",
    var level: Int = 0,
    var profileImageUrl: String = "",
    var profileBackgroundImageUrl: String = "",
    var leagues: MutableList<League> = mutableListOf(),
)

data class ResponseSummoner(
    var summoner: Summoner
)