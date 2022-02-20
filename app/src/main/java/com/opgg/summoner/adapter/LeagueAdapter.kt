package com.opgg.summoner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opgg.summoner.adapter.viewholder.LeagueViewHolder
import com.opgg.summoner.databinding.RowLeagueBinding
import com.opgg.summoner.network.models.League

class LeagueAdapter: RecyclerView.Adapter<LeagueViewHolder>() {
    private val items: MutableList<League> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val binding = RowLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    open fun addItem(item: League) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }
}