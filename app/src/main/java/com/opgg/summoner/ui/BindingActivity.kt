package com.opgg.summoner.ui

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<DataBinding : ViewDataBinding>(@LayoutRes val layoutId: Int): BaseActivity() {
    protected lateinit var binding: DataBinding

    override fun onCreatedView() {
        DataBindingUtil.setContentView<DataBinding>(this, layoutId).also {
            binding = it
            binding.lifecycleOwner = this
        }
        viewBinding()
        init()
    }

    protected abstract fun viewBinding()
}