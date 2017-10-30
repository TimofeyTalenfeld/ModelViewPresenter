package org.sample.mvp.navigation.toolbar

import android.support.v7.widget.Toolbar

class ToolbarHolder(private val toolbar: Toolbar): IToolbarHolder {

    override fun applyTitle(title: String) {
        toolbar.title = title
    }

    override fun applyNavigationClickListener(onClicked: () -> Unit) {
        toolbar.setNavigationOnClickListener { onClicked.invoke() }
    }
}