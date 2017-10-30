package org.sample.mvp.di

import org.sample.mvp.di.app.AppComponent
import org.sample.mvp.di.app.DaggerAppComponent
import org.sample.mvp.di.promo.PromoComponent

object ComponentManager {
    private val appComponent: AppComponent by lazy { DaggerAppComponent.builder().build() }

    private var promoComponent: PromoComponent? = null

    fun promoComponent(): PromoComponent = promoComponent ?:
        appComponent.promoComponentBuilder().build().apply { promoComponent = this }

    fun clearPromoComponent() {
        promoComponent = null
    }
}