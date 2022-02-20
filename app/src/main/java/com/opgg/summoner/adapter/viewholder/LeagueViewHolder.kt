package com.opgg.summoner.adapter.viewholder

import com.opgg.summoner.databinding.RowLeagueBinding
import com.opgg.summoner.network.models.League

class LeagueViewHolder(private val binding: RowLeagueBinding): BaseViewHolder(binding) {

    fun bindView(item: League) {
        binding.vm = LeagueViewHolderVM(item)
    }
}