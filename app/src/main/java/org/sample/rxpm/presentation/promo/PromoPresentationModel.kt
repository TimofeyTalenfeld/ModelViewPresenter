package org.sample.rxpm.presentation.promo

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import org.sample.rxpm.di.ComponentManager
import org.sample.rxpm.di.promo.PromoScope
import org.sample.rxpm.interactor.promo.IPromoInteractor
import org.sample.rxpm.model.promo.Promo
import org.sample.rxpm.presentation.base.BasePresentationModel
import javax.inject.Inject

@PromoScope
class PromoPresentationModel @Inject constructor(val promoInteractor: IPromoInteractor):
    BasePresentationModel(), IPromoPresentationModel {

    override val feed: Relay<List<Promo>> = BehaviorRelay.create()
    override val onPromoClicked: Relay<Promo> = PublishRelay.create()
    override val showWeb: Relay<Pair<String, String>> = PublishRelay.create()

    init {
        feed.accept(emptyList())
        loadFeed()
        onPromoClicked.bind { onPromoClicked(it) }
    }

    private fun onPromoClicked(it: Promo) {
        showWeb.accept(it.url to it.title)
    }

    override fun unbind() {
        super.unbind()
        ComponentManager.clearPromoComponent()
    }

    private fun loadFeed() {
        promoInteractor.getPromos().bind { feed.accept(it) }
    }
}