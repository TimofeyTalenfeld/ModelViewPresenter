package org.sample.rxpm.di.promo

import dagger.Subcomponent
import org.sample.rxpm.view.promo.PromoFragment

@PromoScope
@Subcomponent(modules = arrayOf(PromoModule::class))
interface PromoComponent {

    fun inject(fragment: PromoFragment)

    @Subcomponent.Builder
    interface Builder {
        fun promoModule(module: PromoModule): PromoComponent.Builder
        fun build(): PromoComponent
    }
}
