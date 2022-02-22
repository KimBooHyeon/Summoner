package com.opgg.summoner.ui

import android.content.Intent
import android.widget.Toast
import com.opgg.summoner.R
import com.opgg.summoner.databinding.ActivityMainBinding

class MainActivity: BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun init() {
    }

    override fun viewBinding() {
        binding.activity = this
    }

    fun onSearch() {
        if (binding.etNickname.text.isEmpty()) {
            Toast.makeText(this, getString(R.string.search_error), Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, SummonerActivity::class.java)
            intent.putExtra("nickname", binding.etNickname.text.toString())
            startActivity(intent)
        }
    }
}