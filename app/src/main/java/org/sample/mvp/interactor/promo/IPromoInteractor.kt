package org.sample.mvp.interactor.promo

import io.reactivex.Single
import org.sample.mvp.model.promo.Promo

interface IPromoInteractor {
    fun getPromos(): Single<List<Promo>>
}
