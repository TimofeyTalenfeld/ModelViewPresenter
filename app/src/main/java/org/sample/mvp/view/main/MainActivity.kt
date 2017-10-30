package org.sample.mvp.view.main

import org.sample.mvp.R
import org.sample.mvp.view.base.BaseActivity
import org.sample.mvp.view.promo.PromoFragment

class MainActivity: BaseActivity() {
    override fun getLayoutId() = R.layout.activity_main
    override fun fragment() = PromoFragment::class.java to null
}
