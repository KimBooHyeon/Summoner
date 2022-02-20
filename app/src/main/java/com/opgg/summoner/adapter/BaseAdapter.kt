package com.opgg.summoner.adapter

import androidx.recyclerview.widget.RecyclerView
import com.opgg.summoner.adapter.viewholder.BaseViewHolder

abstract class BaseAdapter<T : BaseViewHolder>: RecyclerView.Adapter<T>() {
    override fun onViewAttachedToWindow(holder: T) {
        super.onViewAttachedToWindow(holder)
        holder.onAppear()
    }

    override fun onViewDetachedFromWindow(holder: T) {
        super.onViewDetachedFromWindow(holder)
        holder.onDisappear()
    }
}