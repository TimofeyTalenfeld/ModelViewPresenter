package org.sample.mvp.view.base

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import org.sample.mvp.navigation.INavigationManager
import org.sample.mvp.navigation.toolbar.IToolbarHolder

abstract class BaseFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI(view)
    }

    @LayoutRes protected abstract fun getLayoutId(): Int

    open protected fun initializeUI(view: View) {}

    open fun onBackPressed() = false

    fun baseActivity(): BaseActivity = activity as BaseActivity

    fun navigation(): INavigationManager = baseActivity().navigationManager

    open fun onToolbarProvided(toolbar: IToolbarHolder) {}

    open fun onRotated() {}
}
