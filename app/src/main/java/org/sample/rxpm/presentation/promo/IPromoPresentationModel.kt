package org.sample.rxpm.presentation.promo

import com.jakewharton.rxrelay2.Relay
import org.sample.rxpm.model.promo.Promo
import org.sample.rxpm.presentation.base.IPresentationModel

interface IPromoPresentationModel: IPresentationModel {
    val feed: Relay<List<Promo>>
    val onPromoClicked: Relay<Promo>
    val showWeb: Relay<Pair<String, String>>
}