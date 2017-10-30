package org.sample.mvp.di.promo

import dagger.Subcomponent
import org.sample.mvp.view.promo.PromoFragment

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
