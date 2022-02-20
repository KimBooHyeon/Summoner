package com.opgg.summoner.ui

import androidx.activity.viewModels
import com.opgg.summoner.R
import com.opgg.summoner.adapter.LeagueAdapter
import com.opgg.summoner.databinding.ActivitySummonerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerActivity : BindingActivity<ActivitySummonerBinding>(R.layout.activity_summoner) {
    private val vm: SummonerVM by viewModels()
    private val adapter: LeagueAdapter by lazy { LeagueAdapter() }

    override fun viewBinding() {
        binding.vm = vm
    }

    override fun init() {
        vm.getSummonerInfo("Genetory")
            .subscribe({
                it.leagues.forEach { league ->
                    adapter.addItem(league)
                }
            }, { t -> t.printStackTrace() })
    }
}