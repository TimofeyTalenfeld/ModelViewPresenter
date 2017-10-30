package org.sample.rxpm.presentation.base

import android.util.Log
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.sample.rxpm.R
import org.sample.rxpm.utils.addTo

abstract class BasePresentationModel : IPresentationModel {

    override val showSnackBar: Relay<Int> = PublishRelay.create()
    override val goBack: Relay<Unit> = PublishRelay.create()

    private val subscriptions = CompositeDisposable()

    override fun unbind() {
        subscriptions.clear()
    }

    protected fun <T> Observable<T>.bind(action: (T) -> Unit) {
        subscribeOn(Schedulers.io())
            .subscribe(action, { onError(it) })
            .addTo(subscriptions)
    }

    protected fun <T> Single<T>.bind(action: (T) -> Unit) {
        subscribeOn(Schedulers.io())
            .subscribe(action, { onError(it) })
            .addTo(subscriptions)
    }

    private fun onError(e: Throwable) {
        Log.e("onError()", e.toString())
        showSnackBar.accept(R.string.some_error_happens)
    }
}
