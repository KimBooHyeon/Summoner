package com.opgg.summoner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.opgg.summoner.databinding.ItemGameBinding
import com.opgg.summoner.network.models.Game

class MatchAdapter: RecyclerView.Adapter<MatchAdapter.GameViewHolder>() {
    private val items: MutableList<Game> = mutableListOf()


    inner class GameViewHolder(private val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Game) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false).let {
            return GameViewHolder(it)
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