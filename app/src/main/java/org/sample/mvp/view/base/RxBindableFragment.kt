package org.sample.mvp.view.base

import android.support.design.widget.Snackbar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import org.sample.mvp.presentation.base.IPresenter
import org.sample.mvp.utils.addTo

abstract class RxBindableFragment<in View: BaseView, out Presenter : IPresenter<View>>: BaseFragment(), BaseView {
    abstract protected val presenter: Presenter

    abstract fun presenter(): IPresenter<View>

    override fun showSnackBar(resId: Int) {
        view?.apply { Snackbar.make(this, getString(resId), Snackbar.LENGTH_SHORT).show() }
    }

    override fun goBack() {
        baseActivity().onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this as View)
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }
}