package com.opgg.summoner.ui

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.appbar.AppBarLayout
import com.opgg.summoner.R
import com.opgg.summoner.adapter.LeagueAdapter
import com.opgg.summoner.adapter.MatchAdapter
import com.opgg.summoner.databinding.ActivitySummonerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class SummonerActivity : BindingActivity<ActivitySummonerBinding>(R.layout.activity_summoner) {
    private val vm: SummonerVM by viewModels()

    override fun viewBinding() {
        vm.nickname.value = intent.getStringExtra("nickname")
        binding.vm = vm
        binding.activity = this
    }

    override fun init() {
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) != appBarLayout.totalScrollRange) {
                binding.tvToolbarTitle.visibility = View.GONE
                binding.ivBack.visibility = View.GONE
            } else {
                binding.tvToolbarTitle.visibility = View.VISIBLE
                binding.ivBack.visibility = View.VISIBLE
            }
        })

        binding.list.let {
            it.adapter = MatchAdapter()
            val animator: RecyclerView.ItemAnimator? = it.itemAnimator
            if (animator is SimpleItemAnimator) {
                animator.supportsChangeAnimations = false
            }
            it.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter?.itemCount
                    if (lastVisibleItemPosition + 5 == itemTotalCount) {
                        vm.getMatchList("Genetory")
                    }
                }
            })
        }
        binding.layoutHeader.listLeague.adapter = LeagueAdapter()

        vm.reset()
    }

    fun back() {
        finish()
    }
}