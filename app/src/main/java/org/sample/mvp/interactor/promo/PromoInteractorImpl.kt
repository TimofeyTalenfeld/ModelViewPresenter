package org.sample.mvp.interactor.promo

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.sample.mvp.model.promo.Promo
import org.sample.mvp.repository.promo.PromoRepositoryImpl

class PromoInteractorImpl: IPromoInteractor {
    override fun getPromos(): Single<List<Promo>> {
        return PromoRepositoryImpl.getPromos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
