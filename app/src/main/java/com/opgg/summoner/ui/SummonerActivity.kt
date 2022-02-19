package com.opgg.summoner.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.opgg.summoner.R
import com.opgg.summoner.databinding.ActivitySummonerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SummonerActivity : BindingActivity<ActivitySummonerBinding>(R.layout.activity_summoner) {
    private val vm: SummonerVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun viewBinding() {
        binding.vm = vm
    }
}