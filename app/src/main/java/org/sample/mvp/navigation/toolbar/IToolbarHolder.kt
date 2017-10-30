package org.sample.mvp.navigation.toolbar

interface IToolbarHolder {
    fun applyTitle(title: String)
    fun applyNavigationClickListener(onClicked: () -> Unit)
}