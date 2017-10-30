package org.sample.rxpm.navigation

import android.content.Intent
import android.os.Bundle
import org.sample.rxpm.navigation.toolbar.IToolbarHolder
import org.sample.rxpm.view.base.BaseActivity
import org.sample.rxpm.view.base.BaseFragment


interface INavigationManager {

    fun showFragment(clazz: Class<out BaseFragment>, args: Bundle?)

    fun showFragment(clazz: Class<out BaseFragment>)

    fun showActivity(clazz: Class<out BaseActivity>)

    fun showActivity(clazz: Class<out BaseActivity>, args: Bundle?)

    fun performIntent(intent: Intent)

    fun onBackPressed(): Boolean

    fun onToolbarProvided(toolbar: IToolbarHolder)

    fun onRotated()
}