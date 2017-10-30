package org.sample.rxpm.utils

import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

fun setupSpan(view: View) {
    val layoutParams = view.layoutParams as? StaggeredGridLayoutManager.LayoutParams ?: return
    layoutParams.isFullSpan = true
}
