package com.opgg.summoner.adapter.viewholder

import com.opgg.summoner.network.models.Game
import com.opgg.summoner.ui.BaseVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewHolderVM @Inject constructor(item: Game): BaseVM() {
    var champion = item.champion
    var spells = item.spells
    var peak = item.peak
    var gameType = item.gameType
    var stats = item.stats
    var isWin = item.isWin
}