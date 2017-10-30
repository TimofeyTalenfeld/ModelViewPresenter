package org.sample.rxpm.view.base

import android.support.design.widget.Snackbar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.sample.rxpm.presentation.base.IPresentationModel
import org.sample.rxpm.utils.addTo

abstract class RxBindableFragment<out Model : IPresentationModel>: BaseFragment() {
    abstract protected val model: Model
    private val viewSubscriptions by lazy { CompositeDisposable() }

    override fun onStart() {
        super.onStart()
        bindBase()
        onBound()
    }

    private fun bindBase() {
        model.showSnackBar.bindToView { showSnackBar(getString(it)) }
        model.goBack.bindToView { baseActivity().onBackPressed() }
    }

    protected fun showSnackBar(message: String) {
        view?.apply { Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show() }
    }

    override fun onBackPressed(): Boolean {
        model.unbind()
        return super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        viewSubscriptions.clear()
    }

    abstract protected fun onBound()

    protected fun <T> Observable<T>.bindToView(consumer: (T) -> Unit) {
        observeOn(AndroidSchedulers.mainThread())
            .subscribe(consumer)
            .addTo(viewSubscriptions)
    }
}