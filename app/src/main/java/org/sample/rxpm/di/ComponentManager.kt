package org.sample.rxpm.di

import org.sample.rxpm.di.app.AppComponent
import org.sample.rxpm.di.app.DaggerAppComponent
import org.sample.rxpm.di.promo.PromoComponent

object ComponentManager {
    private val appComponent: AppComponent by lazy { DaggerAppComponent.builder().build() }

    private var promoComponent: PromoComponent? = null

    fun promoComponent(): PromoComponent = promoComponent ?:
        appComponent.promoComponentBuilder().build().apply { promoComponent = this }

    fun clearPromoComponent() {
        promoComponent = null
    }
}