package com.opgg.summoner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.opgg.summoner.adapter.viewholder.BaseViewHolder
import com.opgg.summoner.adapter.viewholder.LeagueViewHolderVM
import com.opgg.summoner.databinding.ItemLeagueBinding
import com.opgg.summoner.network.models.League
import com.opgg.summoner.ui.Global

class LeagueAdapter: BaseAdapter<LeagueAdapter.LeagueViewHolder>() {
    private val items: MutableList<League> = mutableListOf()
    lateinit var context: Context

    inner class LeagueViewHolder(private val binding: ItemLeagueBinding): BaseViewHolder(binding) {
        fun bindView(item: League) {
            binding.vm = LeagueViewHolderVM(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        context = parent.context
        ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            val holder = LeagueViewHolder(it)
            it.lifecycleOwner = holder
            return holder
        }
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val params = (holder.itemView.layoutParams as ViewGroup.MarginLayoutParams).apply {
            marginStart = if (position > 0) Global.INSTANCE.convertDpToPixel(context, 8) else 0
        }
        holder.itemView.layoutParams = params
        holder.itemView.requestLayout()
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItem(leagues: MutableList<League>) {
        items.clear()
        items.addAll(leagues)
        notifyDataSetChanged()
    }
}