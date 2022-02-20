package com.opgg.summoner.ui

import android.view.View
import androidx.activity.viewModels
import com.google.android.material.appbar.AppBarLayout
import com.opgg.summoner.R
import com.opgg.summoner.adapter.LeagueAdapter
import com.opgg.summoner.databinding.ActivitySummonerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class SummonerActivity : BindingActivity<ActivitySummonerBinding>(R.layout.activity_summoner) {
    private val vm: SummonerVM by viewModels()
    private val adapter: LeagueAdapter by lazy { LeagueAdapter() }

    override fun viewBinding() {
        binding.vm = vm
    }

    override fun init() {
        binding.layoutHeader.listLeague.adapter = adapter
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            binding.tvToolbarTitle.visibility = if (abs(verticalOffset) != appBarLayout.totalScrollRange) View.INVISIBLE else View.VISIBLE
        })

        vm.getSummonerInfo("Genetory")
            .subscribe({
                vm.leagues.value = it.leagues
                it.leagues.forEach { league ->
                    adapter.addItem(league)
                }
            }, { t -> t.printStackTrace() })
    }
}