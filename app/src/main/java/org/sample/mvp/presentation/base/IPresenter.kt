package org.sample.mvp.presentation.base

import org.sample.mvp.view.base.BaseView

interface IPresenter<in View: BaseView> {
    fun bind(view: View)
    fun unbind()
    fun onBackPressed()
}
