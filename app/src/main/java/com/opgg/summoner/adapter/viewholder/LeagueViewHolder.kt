package com.opgg.summoner.adapter.viewholder

import com.opgg.summoner.databinding.ItemLeagueBinding
import com.opgg.summoner.network.models.League

class LeagueViewHolder(private val binding: ItemLeagueBinding): BaseViewHolder(binding) {

    fun bindView(item: League) {
        binding.vm = LeagueViewHolderVM(item)
    }
}