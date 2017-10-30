package org.sample.rxpm.navigation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import org.sample.rxpm.R
import org.sample.rxpm.navigation.toolbar.IToolbarHolder
import org.sample.rxpm.view.base.BaseFragment
import org.sample.rxpm.view.base.BaseActivity


class NavigationManager constructor(val activity: BaseActivity) : INavigationManager {

    private val fragmentManager = activity.supportFragmentManager

    override fun showFragment(clazz: Class<out BaseFragment>) {
        showFragment(clazz, null)
    }

    override fun onBackPressed(): Boolean {
        if (!areFragmentsPresent()) return false

        if ((fragments()?.last() as BaseFragment).onBackPressed()) {
            return true
        }

        if (fragments()?.size == 1) {
            return false
        }

        fragmentManager.popBackStackImmediate()

        return areFragmentsPresent()
    }

    override fun onRotated() {
        if (!areFragmentsPresent()) return
        fragments()?.map { it as BaseFragment }?.forEach { it.onRotated() }
    }

    private fun areFragmentsPresent(): Boolean {
        fragments()?.apply { return isNotEmpty() }
        return false
    }

    override fun showActivity(clazz: Class<out BaseActivity>) {
        showActivity(clazz, null)
    }

    override fun showActivity(clazz: Class<out BaseActivity>, args: Bundle?) {
        activity.startActivity(Intent(activity, clazz).apply { putExtras(args) })
    }

    override fun performIntent(intent: Intent) {
        activity.startActivity(intent)
    }

    override fun onToolbarProvided(toolbar: IToolbarHolder) {
        activity.onToolbarProvided(toolbar)
        fragments()?.map { it as BaseFragment }?.forEach { it.onToolbarProvided(toolbar) }
    }

    private fun fragments(): List<Fragment>? {
        return fragmentManager.fragments?.filter { it != null }
    }

    override fun showFragment(clazz: Class<out BaseFragment>, args: Bundle?) {
        fragmentManager
            .beginTransaction()
            .replace(R.id.content, Fragment.instantiate(activity.applicationContext, clazz.canonicalName, args))
            .commit()
    }

    companion object {
        fun from(activity: BaseActivity): INavigationManager {
            return NavigationManager(activity)
        }
    }
}