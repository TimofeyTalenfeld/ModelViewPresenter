package org.sample.mvp.presentation.promo

import org.sample.mvp.di.ComponentManager
import org.sample.mvp.di.promo.PromoScope
import org.sample.mvp.interactor.promo.IPromoInteractor
import org.sample.mvp.model.promo.Promo
import org.sample.mvp.presentation.base.BasePresenter
import org.sample.mvp.view.promo.PromoView
import org.sample.mvp.viewstate.promo.PromoViewState
import javax.inject.Inject

@PromoScope
class PromoPresenter @Inject constructor(
        private val viewState: PromoViewState,
        private val promoInteractor: IPromoInteractor
): BasePresenter<PromoView, PromoViewState>(viewState) {

    init {
        viewState.showFeed(emptyList())
        loadFeed()
    }

    fun onPromoClicked(it: Promo) {
        viewState.showWeb(it.url, it.title)
    }

    override fun unbind() {
        super.unbind()
        ComponentManager.clearPromoComponent()
    }

    private fun loadFeed() {
        promoInteractor.getPromos().bind { viewState.showFeed(it) }
    }
}