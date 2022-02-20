package com.opgg.summoner.adapter.viewholder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.opgg.summoner.network.models.League
import com.opgg.summoner.network.models.Summoner

class LeagueViewHolder(binding: ViewDataBinding): BaseViewHolder(binding) {
    fun bindView(item: League) {}
}