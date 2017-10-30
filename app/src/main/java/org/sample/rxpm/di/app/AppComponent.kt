package org.sample.rxpm.di.app

import dagger.Component
import org.sample.rxpm.di.promo.PromoComponent
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun promoComponentBuilder(): PromoComponent.Builder
}
