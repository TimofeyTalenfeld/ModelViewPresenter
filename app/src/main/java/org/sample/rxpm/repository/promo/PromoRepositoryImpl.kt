package org.sample.rxpm.repository.promo

import io.reactivex.Single
import org.sample.rxpm.model.promo.Promo

object PromoRepositoryImpl: IPromoRepository {

    override fun getPromos(): Single<List<Promo>> = Single.fromCallable { PROMO }

    private val PROMO: List<Promo> = listOf(
            Promo("Кузовные запчасти", "https://m.auto.ru/parts/?categoryId=16"),
            Promo("Масла и автохимия", "https://m.auto.ru/parts/?categoryId=2022"),
            Promo("Аккумуляторы", "https://m.auto.ru/parts/?categoryId=1544"),
            Promo("Шины и диски", "hhttps://m.auto.ru/parts/?categoryId=3"),
            Promo("Двигатель и система зажигания", "https://m.auto.ru/parts/?categoryId=6")
    )
}
