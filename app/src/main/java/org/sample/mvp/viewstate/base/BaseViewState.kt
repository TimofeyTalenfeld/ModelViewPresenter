package org.sample.mvp.viewstate.base

import org.sample.mvp.view.base.BaseView

abstract class BaseViewState<View: BaseView>: BaseView {

    protected var view: View? = null
    private var snackBarResId: Int? = null

    fun bind(view: View) {
        this.view = view
    }

    fun unbind() {
        view = null
    }

    override fun goBack() {
        view?.goBack()
    }

    override fun showSnackBar(resId: Int) {
        snackBarResId = resId
        view?.showSnackBar(resId)
    }

    open fun restore() {
        snackBarResId?.let { view?.showSnackBar(it) }
    }
}