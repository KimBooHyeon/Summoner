package com.opgg.summoner.adapter.viewholder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.opgg.summoner.network.models.Summoner

abstract class BaseViewHolder(binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root) {
//    fun onBind(vm: SummonerViewHolderVM?, item: Summoner) {
////        if (vm != null) binding.setVariable(BR.vm, vm)
////        binding.setVariable(BR.item, item)
////        binding.executePendingBindings()
//    }
}