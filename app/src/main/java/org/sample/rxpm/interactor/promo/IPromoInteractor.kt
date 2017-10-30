package org.sample.rxpm.interactor.promo

import io.reactivex.Single
import org.sample.rxpm.model.promo.Promo

interface IPromoInteractor {
    fun getPromos(): Single<List<Promo>>
}
