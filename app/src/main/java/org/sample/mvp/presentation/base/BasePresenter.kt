package org.sample.mvp.presentation.base

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.sample.mvp.R
import org.sample.mvp.utils.addTo
import org.sample.mvp.view.base.BaseView
import org.sample.mvp.viewstate.base.BaseViewState

abstract class BasePresenter<View: BaseView, ViewState: BaseViewState<View>>
(private val viewState: ViewState) : IPresenter<View> {
    private val subscriptions = CompositeDisposable()
    private val viewSubscriptions by lazy { CompositeDisposable() }

    override fun bind(view: View) {
        viewState.bind(view)
        viewState.restore()
    }

    override fun unbind() {
        viewState.unbind()
        viewSubscriptions.clear()
    }

    override fun onBackPressed() {
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

    protected fun <T> Observable<T>.bindToView(consumer: (T) -> Unit) {
        observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer)
                .addTo(viewSubscriptions)
    }

    private fun onError(e: Throwable) {
        Log.e("onError()", e.toString())
        viewState.showSnackBar(R.string.some_error_happens)
    }
}
