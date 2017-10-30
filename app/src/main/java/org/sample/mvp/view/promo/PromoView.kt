package org.sample.mvp.view.promo

import org.sample.mvp.model.promo.Promo
import org.sample.mvp.view.base.BaseView

interface PromoView: BaseView {
    fun showWeb(title: String, url: String)
    fun showFeed(items: List<Promo>)
}