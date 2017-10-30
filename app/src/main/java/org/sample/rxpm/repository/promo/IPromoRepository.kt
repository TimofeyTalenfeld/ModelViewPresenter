package org.sample.rxpm.repository.promo

import io.reactivex.Single
import org.sample.rxpm.model.promo.Promo

interface IPromoRepository {
    fun getPromos(): Single<List<Promo>>
}
