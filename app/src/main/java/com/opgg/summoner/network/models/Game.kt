package com.opgg.summoner.network.models

data class Game(
    var champion: Champion,
    var spells: MutableList<ImageUrl>,
    var peak: MutableList<String>,
    var items: MutableList<ImageUrl>,
    var stats: Stats,
    var createDate: Long,
    var gameLength: Int,
    var gameType: String,
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
            var assist: Int,
            var contributionForKillRate: String,
            var largestMultiKillString: String,
            var opScoreBadge: String
        )
    }
}

data class ResponseGame(
    var games: MutableList<Game>
)