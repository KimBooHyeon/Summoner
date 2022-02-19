package com.opgg.summoner.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<DataBinding : ViewDataBinding>(@LayoutRes val layoutId: Int): BaseActivity() {
    private lateinit var binding: DataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<DataBinding>(this, layoutId).also { binding = it }
    }
}