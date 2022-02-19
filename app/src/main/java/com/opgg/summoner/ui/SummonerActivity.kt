package com.opgg.summoner.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.opgg.summoner.R
import com.opgg.summoner.databinding.ActivitySummonerBinding

class SummonerActivity : BindingActivity<ActivitySummonerBinding>(R.layout.activity_summoner) {
    // private val vm: CommunityDetailVM by inject()
//    private val vm: SummonerVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun viewBinding() {
        binding.vm = ViewModelProvider(this)[SummonerVM::class.java]
    }
}