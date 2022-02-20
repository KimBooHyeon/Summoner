package com.opgg.summoner.ui

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseVM : ViewModel() {
    private val compositeDisposable by lazy { CompositeDisposable() }

    protected fun Disposable.addToDisposable(): Disposable = apply { compositeDisposable.add(this) }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}