package org.sample.mvp.view.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import org.sample.mvp.R
import org.sample.mvp.navigation.NavigationManager
import org.sample.mvp.navigation.toolbar.IToolbarHolder
import org.sample.mvp.navigation.toolbar.ToolbarHolder
import org.sample.mvp.utils.isLarge

abstract class BaseActivity: AppCompatActivity() {

    val navigationManager by lazy { NavigationManager.from(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (!isLarge()) {
            restrictOnlyPortraitOrientation()
        }
        setupFragment()
    }

    @LayoutRes protected abstract fun getLayoutId(): Int

    open protected fun fragment(): Pair<Class<out BaseFragment>, Bundle?>? { return null }

    fun setupFragment() {
        val fragment = fragment()
        if (fragment != null) {
            navigationManager.showFragment(fragment.first, fragment.second)
        }
    }

    override fun onResume() {
        super.onResume()
        setupToolbar()
    }

    private fun setupToolbar() {
        findViewById(R.id.toolbar)?.apply {
            navigationManager.onToolbarProvided(ToolbarHolder(this as Toolbar))
        }
    }

    private fun restrictOnlyPortraitOrientation() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onBackPressed() {
        if (navigationManager.onBackPressed()) return
        super.onBackPressed()
    }

    open fun onToolbarProvided(toolbar: IToolbarHolder) {}
}