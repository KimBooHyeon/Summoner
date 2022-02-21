package com.opgg.summoner.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.opgg.summoner.adapter.GameAdapter
import com.opgg.summoner.adapter.LeagueAdapter
import com.opgg.summoner.network.models.Game
import com.opgg.summoner.network.models.League

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .apply(
                RequestOptions.bitmapTransform(CenterCrop())
            )
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
    }
}

@BindingAdapter("leagues")
fun RecyclerView.setLeagues(items: MutableList<League>) {
    items.let {
        (adapter as LeagueAdapter).setItem(it)
    }
}

@BindingAdapter("games")
fun RecyclerView.setGames(items: MutableList<Game>) {
    items.let {
        (adapter as GameAdapter).setItem(items)
    }
}