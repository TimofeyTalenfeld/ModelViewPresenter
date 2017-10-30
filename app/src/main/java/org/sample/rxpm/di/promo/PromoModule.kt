package org.sample.rxpm.di.promo

import dagger.Module
import dagger.Provides
import org.sample.rxpm.interactor.promo.IPromoInteractor
import org.sample.rxpm.interactor.promo.PromoInteractorImpl

@Module
class PromoModule {
    @Provides
    @PromoScope
    fun providePromoInteractor(): IPromoInteractor = PromoInteractorImpl()
}
