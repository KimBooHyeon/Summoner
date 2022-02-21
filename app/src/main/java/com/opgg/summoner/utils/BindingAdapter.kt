package com.opgg.summoner.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.opgg.summoner.R
import com.opgg.summoner.adapter.GameAdapter
import com.opgg.summoner.adapter.LeagueAdapter
import com.opgg.summoner.databinding.ItemGameBinding
import com.opgg.summoner.databinding.ItemImageRoundedBinding
import com.opgg.summoner.network.models.Game
import com.opgg.summoner.network.models.League
import com.opgg.summoner.ui.Global

@BindingAdapter("image")
fun ImageView.image(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(this)
    }
}

@BindingAdapter("image_circle")
fun ImageView.imageCircle(src: String?) {
    Glide.with(context)
        .load(src)
        .apply(RequestOptions.bitmapTransform(CenterCrop()))
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

@BindingAdapter("image_round")
fun ImageView.imageRound(src: String?) {
    Glide.with(this.context)
        .load(src)
        .apply(
            RequestOptions.bitmapTransform(
                MultiTransformation(
                    CenterCrop(),
                    RoundedCorners(Global.INSTANCE.convertDpToPixel(context, 4))
                )
            )
        )
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

@BindingAdapter("item_images")
fun LinearLayout.itemImages(items: MutableList<Game.ImageUrl>) {
    this.removeAllViews()
    for (i: Int in 0 until 6) {
        val binding = DataBindingUtil.inflate<ItemImageRoundedBinding>(
            LayoutInflater.from(context), R.layout.item_image_rounded, null, false
        ).apply {
            if (i < items.size) {
                src = items[i].imageUrl
            }
        }
        this.addView(binding.root)
    }
    Global.INSTANCE.dLog("size : ${this.childCount}")
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