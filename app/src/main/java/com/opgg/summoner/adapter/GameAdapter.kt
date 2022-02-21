package com.opgg.summoner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.opgg.summoner.adapter.viewholder.BaseViewHolder
import com.opgg.summoner.databinding.ItemGameBinding
import com.opgg.summoner.network.models.Game

class GameAdapter: BaseAdapter<GameAdapter.GameViewHolder>() {
    private val items: MutableList<Game> = mutableListOf()


    inner class GameViewHolder(private val binding: ItemGameBinding): BaseViewHolder(binding) {
        fun bindView(item: Game) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            val holder = GameViewHolder(it)
            it.lifecycleOwner = holder
            return holder
        }
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItem(games: MutableList<Game>) {
        items.clear()
        items.addAll(games)
        notifyDataSetChanged()
    }
}