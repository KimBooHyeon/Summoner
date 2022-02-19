package com.opgg.summoner.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {
    private val compositeDisposable by lazy { CompositeDisposable() }
    protected fun Disposable.addToDisposable(): Disposable = apply { compositeDisposable.add(this) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}