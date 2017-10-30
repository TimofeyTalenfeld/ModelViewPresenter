package org.sample.mvp.repository.promo

import io.reactivex.Single
import org.sample.mvp.model.promo.Promo

interface IPromoRepository {
    fun getPromos(): Single<List<Promo>>
}
