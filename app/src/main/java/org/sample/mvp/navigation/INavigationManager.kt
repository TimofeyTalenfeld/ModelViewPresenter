package org.sample.mvp.navigation

import android.content.Intent
import android.os.Bundle
import org.sample.mvp.navigation.toolbar.IToolbarHolder
import org.sample.mvp.view.base.BaseActivity
import org.sample.mvp.view.base.BaseFragment


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