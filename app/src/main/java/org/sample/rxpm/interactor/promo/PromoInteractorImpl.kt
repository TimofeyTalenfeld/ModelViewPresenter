package org.sample.rxpm.interactor.promo

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sample.rxpm.model.promo.Promo
import org.sample.rxpm.repository.promo.PromoRepositoryImpl

class PromoInteractorImpl: IPromoInteractor {
    override fun getPromos(): Single<List<Promo>> {
        return PromoRepositoryImpl.getPromos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
