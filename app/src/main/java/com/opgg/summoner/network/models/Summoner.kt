package com.opgg.summoner.network.models

import android.os.Parcelable

data class Summoner(
    var name: String = "",
    var level: Int = 0,
    var profileImageUrl: String = "",
    var leagues: MutableList<League> = mutableListOf(),
)