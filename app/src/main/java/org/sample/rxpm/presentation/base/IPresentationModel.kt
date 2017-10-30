package org.sample.rxpm.presentation.base

import com.jakewharton.rxrelay2.Relay

interface IPresentationModel {
    val showSnackBar: Relay<Int>
    val goBack: Relay<Unit>
    fun unbind()
}
