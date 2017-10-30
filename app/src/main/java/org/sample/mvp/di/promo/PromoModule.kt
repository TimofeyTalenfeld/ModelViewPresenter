package org.sample.mvp.di.promo

import dagger.Module
import dagger.Provides
import org.sample.mvp.interactor.promo.IPromoInteractor
import org.sample.mvp.interactor.promo.PromoInteractorImpl
import org.sample.mvp.viewstate.promo.PromoViewState

@Module
class PromoModule {
    @Provides
    @PromoScope
    fun providePromoInteractor(): IPromoInteractor = PromoInteractorImpl()

    @Provides
    @PromoScope
    fun provideViewState(): PromoViewState = PromoViewState()
}
