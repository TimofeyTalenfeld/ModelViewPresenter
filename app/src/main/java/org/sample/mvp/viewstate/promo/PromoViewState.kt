package org.sample.mvp.viewstate.promo

import org.sample.mvp.model.promo.Promo
import org.sample.mvp.view.promo.PromoView
import org.sample.mvp.viewstate.base.BaseViewState

class PromoViewState : BaseViewState<PromoView>(), PromoView {

    private var webTitle: String? = null
    private var webUrl: String? = null

    private var items: List<Promo>? = null

    override fun showWeb(title: String, url: String) {
        this.webTitle = title
        this.webUrl = url
        view?.showWeb(title, url)
    }

    override fun showFeed(items: List<Promo>) {
        this.items = items
        view?.showFeed(items)
    }

    override fun restore() {
        super.restore()
        webTitle?.let { title -> webUrl?.let {  url -> view?.showWeb(title, url) } }
        items?.let { view?.showFeed(it) }
    }
}