package org.sample.mvp.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(subscriptions: CompositeDisposable) = subscriptions.add(this)