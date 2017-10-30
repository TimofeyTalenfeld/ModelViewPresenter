package org.sample.rxpm.navigation.toolbar

interface IToolbarHolder {
    fun applyTitle(title: String)
    fun applyNavigationClickListener(onClicked: () -> Unit)
}