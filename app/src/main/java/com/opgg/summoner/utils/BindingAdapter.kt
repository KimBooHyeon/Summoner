package com.opgg.summoner.utils

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.opgg.summoner.R
import com.opgg.summoner.adapter.MatchAdapter
import com.opgg.summoner.adapter.LeagueAdapter
import com.opgg.summoner.databinding.ItemImageRoundedBinding
import com.opgg.summoner.Global
import com.opgg.summoner.network.models.*
import java.lang.Exception

@BindingAdapter("image")
fun ImageView.image(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
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
@BindingAdapter("position_image")
fun ImageView.setPositionImage(position: String?) {
    Glide.with(context)
        .load(when(position) {
            "ADC" -> R.drawable.icon_lol_bot
            "TOP" -> R.drawable.icon_lol_top
            "MID" -> R.drawable.icon_lol_mid
            "SUP" -> R.drawable.icon_lol_sup
            else -> R.drawable.icon_lol_jng
        })
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(this)
}

@BindingAdapter("game_length")
fun TextView.setGameLength(seconds: Int) {
    val formatted = "${(seconds / 60).toString().padStart(2, '0')}:${(seconds % 60).toString().padStart(2, '0')}"
    text = formatted
}

@BindingAdapter("kill", "assist", "death")
fun TextView.setScore(kill: String, assist: String, death: String) {
    val assistHtmlString = "<font color='#E84057'>$assist</font>"
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml("$kill / $assistHtmlString / $death", Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml("$kill / $assistHtmlString / $death")
    }
}

@BindingAdapter("winning_rate_games", "winning_rate_wins")
fun TextView.setWinningRate(games: Int, wins: Int) {
    var percentage = 0
    try {
        percentage = wins.times(100).div(games)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    text = String.format("%d%%", percentage)
}

@BindingAdapter("champion_score")
fun TextView.setChampionScore(summary: Summary?) {
    var wins = 0
    var losses = 0
    summary?.let {
        wins = it.wins
        losses = it.loses
    }
    text = String.format("%d승 %d패", wins, losses)
}

@BindingAdapter("champion_kills")
fun TextView.setChampionKills(summary: Summary?) {
    var kills = 0
    var assists = 0
    var deaths = 0
    summary?.let {
        kills = summary.kills
        assists = summary.assists
        deaths = summary.deaths
    }
    val assistHtmlString = "<font color='#E84057'>$assists</font>"
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml("$kills / $assistHtmlString / $deaths", Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml("$kills / $assistHtmlString / $deaths")
    }
}

@BindingAdapter("champion_kda")
fun TextView.setChampionKDA(summary: Summary?) {
    var kda = "0"
    var percentage = 0
    summary?.let {
        kda = String.format("%.2f", ((summary.kills.toFloat() + summary.assists.toFloat()) / summary.deaths.toFloat()))
        percentage = (summary.wins + summary.assists) / (summary.wins + summary.assists + summary.deaths)
    }
    val kdaHtmlString = "<font color='#1EA1F7'>$kda:1</font>"
    val percentageHtmlString = "<font color='#E84057'>($percentage%)</font>"

    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml("$kdaHtmlString $percentageHtmlString", Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml("$kdaHtmlString $percentageHtmlString")
    }
}

@BindingAdapter("format_time_string")
fun TextView.setFormatTimeString(time: Long) {
    val currentTime = System.currentTimeMillis()
    var diffTime = (currentTime / 1000) - time
    if (diffTime < Constants.MAX_SEC) {
        text = String.format(context.getString(R.string.time_seconds_ago))
        return
    }
    diffTime /= Constants.MAX_SEC
    if (diffTime < Constants.MAX_MIN) {
        text = String.format(context.getString(R.string.time_minutes_ago), diffTime)
        return
    }
    diffTime /= Constants.MAX_MIN
    if (diffTime < Constants.MAX_HOUR) {
        text = String.format(context.getString(R.string.time_hours_ago), diffTime)
        return
    }
    diffTime /= Constants.MAX_HOUR
    if (diffTime < Constants.MAX_DAY) {
        text = String.format(context.getString(R.string.time_days_ago), diffTime)
        return
    }
    diffTime /= Constants.MAX_DAY
    if (diffTime < Constants.MAX_MONTH) {
        text = String.format(context.getString(R.string.time_months_ago), diffTime)
        return
    } else {
        diffTime /= Constants.MAX_MONTH
        text = String.format(context.getString(R.string.time_years_ago), diffTime)
    }
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
        (adapter as MatchAdapter).setItem(items)
    }
}