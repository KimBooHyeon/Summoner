package com.opgg.summoner.network.models

data class Game(
    var champion: Champion,
    var spells: MutableList<ImageUrl>,
    var items: MutableList<ImageUrl>,
    var stats: Stats,
    var isWin: Boolean,
) {
    data class Champion(
        var imageUrl: String,
        var level: Int = 0,
    )

    data class ImageUrl(
        var imageUrl: String,
    )

    data class Stats(
        var general: General
    ) {
        data class General(
            var kill: Int,
            var death: Int,
            var asist: Int,
            var contributionForKillRate: String,
            var largestMultiKillString: String,
            var opScoreBadge: String
        )
    }
}

data class ResponseGame(
    var games: MutableList<Game>
)